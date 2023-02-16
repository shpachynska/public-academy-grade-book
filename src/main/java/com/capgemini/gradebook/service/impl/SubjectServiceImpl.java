package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.SubjectEto;
import com.capgemini.gradebook.domain.mapper.SubjectMapper;
import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.SubjectEntity;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.persistence.repo.ClassRepo;
import com.capgemini.gradebook.persistence.repo.SubjectRepo;
import com.capgemini.gradebook.persistence.repo.TeacherRepo;
import com.capgemini.gradebook.service.SubjectService;

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
    return result.map(r -> SubjectMapper.mapToETO(r)).orElseThrow(() -> new RuntimeException("Subject not found " +
        "exception"));
  }

  @Override
  public SubjectEto save(SubjectEto newSubject) {
    SubjectEntity subjectEntity = SubjectMapper.mapToEntity(newSubject);

    Optional<ClassYearEntity> classYearEntity = this.classYearRepository.findById(newSubject.getClassYearId());
    Optional<TeacherEntity> teacherEntity = this.teacherRepository.findById(newSubject.getTeacherId());

    subjectEntity.setTeacherEntity(teacherEntity.get());
    subjectEntity.setClassYearEntity(classYearEntity.get());
    newSubject.setName(subjectEntity.getSubjectType().toString().concat("_" + subjectEntity.getClassYearEntity().getClassYear()));

    subjectEntity = this.subjectRepository.save(subjectEntity);
    return SubjectMapper.mapToETO(subjectEntity);
  }

  @Override
  public void delete(Long id) {
    this.subjectRepository.deleteById(id);
  }
}
