package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.GradeEto;
import com.capgemini.gradebook.domain.mapper.GradeMapper;
import com.capgemini.gradebook.persistence.entity.GradeEntity;
import com.capgemini.gradebook.persistence.entity.StudentEntity;
import com.capgemini.gradebook.persistence.entity.SubjectEntity;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.persistence.repo.GradeRepo;
import com.capgemini.gradebook.persistence.repo.StudentRepo;
import com.capgemini.gradebook.persistence.repo.SubjectRepo;
import com.capgemini.gradebook.persistence.repo.TeacherRepo;
import com.capgemini.gradebook.service.GradeService;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

  private final GradeRepo gradeRepository;

  private final TeacherRepo teacherRepository;

  private final StudentRepo studentRepository;

  private final SubjectRepo subjectRepository;

  @Autowired
  public GradeServiceImpl(final GradeRepo gradeRepository, TeacherRepo teacherRepository,
      StudentRepo studentRepository, SubjectRepo subjectRepository) {
    this.gradeRepository = gradeRepository;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;
    this.subjectRepository = subjectRepository;
  }

  @Override
  public List<GradeEto> findAllGrades() {return GradeMapper.mapToETOList(this.gradeRepository.findAll()); }

  @Override
  public GradeEto findGradeById(Long id) {
    final Optional<GradeEntity> result = this.gradeRepository.findById(id);
    return result.map(r -> GradeMapper.mapToETO(r)).orElseThrow(() -> new RuntimeException("Grade not found" +
        "exception"));
  }

  @Override
  public GradeEto save(GradeEto newGrade) {
    GradeEntity gradeEntity = GradeMapper.mapToEntity(newGrade);

    Optional<TeacherEntity> teacherEntity = this.teacherRepository.findById(newGrade.getTeacherId());
    Optional<StudentEntity> studentEntity = this.studentRepository.findById(newGrade.getStudentId());
    Optional<SubjectEntity> subjectEntity = this.subjectRepository.findById(newGrade.getSubjectId());

    gradeEntity.setTeacherEntity(teacherEntity.get());
    gradeEntity.setStudentEntity(studentEntity.get());
    gradeEntity.setSubjectEntity(subjectEntity.get());

    gradeEntity = this.gradeRepository.save(gradeEntity);
    return GradeMapper.mapToETO(gradeEntity);
  }

  @Override
  public void delete(Long id) {this.gradeRepository.deleteById(id);}
}
