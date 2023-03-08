package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.SubjectEto;
import com.capgemini.gradebook.domain.mapper.SubjectMapper;
import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.SubjectEntity;
import com.capgemini.gradebook.persistence.entity.SubjectType;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.persistence.repo.ClassRepo;
import com.capgemini.gradebook.persistence.repo.SubjectRepo;
import com.capgemini.gradebook.persistence.repo.TeacherRepo;
import com.capgemini.gradebook.service.SubjectService;
import com.capgemini.gradebook.service.exception.BadRequestException;
import com.capgemini.gradebook.service.exception.NotFoundException;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

  private final SubjectRepo subjectRepository;

  private final TeacherRepo teacherRepository;

  private final ClassRepo classYearRepository;

  @Autowired
  public SubjectServiceImpl(final SubjectRepo subjectRepository, TeacherRepo teacherRepository,
      ClassRepo classYearRepository) {
    this.subjectRepository = subjectRepository;
    this.teacherRepository = teacherRepository;
    this.classYearRepository = classYearRepository;
  }

  @Override
  public List<SubjectEto> findAllSubjects() {

    return SubjectMapper.mapToETOList(this.subjectRepository.findAll());
  }

  @Override
  public SubjectEto findSubjectById(Long id) {

    final Optional<SubjectEntity> result = this.subjectRepository.findById(id);
    return result.map(r -> SubjectMapper.mapToETO(r)).orElseThrow(() -> new NotFoundException("Subject not found"));
  }

  @Override
  public SubjectEto save(SubjectEto newSubject) {

    if (newSubject.getId() != null) {
      throw new BadRequestException("Subject with such id already exists");
    }

    SubjectEntity subjectEntity = SubjectMapper.mapToEntity(newSubject);
    if (newSubject.getTeacherId() == null) {
      throw new NotFoundException("Subject has no teacher id");
    } else {
      Optional<TeacherEntity> teacherEntity = this.teacherRepository.findById(newSubject.getTeacherId());
      if(teacherEntity.isPresent()) {
        subjectEntity.setTeacherEntity(teacherEntity.get());
      } else {
        throw new NotFoundException("There is no teacher with such id in db");
      }
    }

    if (newSubject.getClassYearId() == null) {
      throw new NotFoundException("Subject has no class year");
    } else {
      Optional<ClassYearEntity> classYearEntity = this.classYearRepository.findById(newSubject.getClassYearId());
      if(classYearEntity.isPresent()) {
        subjectEntity.setClassYearEntity(classYearEntity.get());
      } else {
        throw new NotFoundException("There is no class year with such id in db");
      }
    }

    newSubject.setName(subjectEntity.getSubjectType().toString().concat("_" + subjectEntity.getClassYearEntity().getClassYear()));

    subjectEntity = this.subjectRepository.save(subjectEntity);
    return SubjectMapper.mapToETO(subjectEntity);
  }

  @Override
  public SubjectEto updateSubject(SubjectEto subjectEto, Long subjectId) {

    if(!subjectRepository.existsById(subjectId)) {
      throw new NotFoundException("Subject with id " + subjectId + " doesn't exist");
    }

    SubjectEntity existingSubject = subjectRepository.getOne(subjectId);
    TeacherEntity teacher = teacherRepository.findById(subjectEto.getTeacherId())
            .orElseThrow(() -> new NotFoundException("There is no such teacher in database"));
    ClassYearEntity classYear = classYearRepository.findById(subjectEto.getClassYearId())
            .orElseThrow(() -> new NotFoundException("There is no such class year in database"));

    subjectEto.setVersion(existingSubject.getVersion());
    subjectEto.setId(subjectId);

    SubjectEntity subjectToUpdate = SubjectMapper.mapToEntity(subjectEto);
    subjectToUpdate.setTeacherEntity(teacher);
    subjectToUpdate.setClassYearEntity(classYear);

    return SubjectMapper.mapToETO(subjectRepository.save(subjectToUpdate));
  }

  @Override
  public void delete(Long id) {

    if (!subjectRepository.existsById(id)) {
      throw new NotFoundException("Subject with id " + id + " does not exist");
    }

    this.subjectRepository.deleteById(id);
  }
}
