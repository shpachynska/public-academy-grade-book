package com.capgemini.gradebook.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.GradeEto;
import com.capgemini.gradebook.domain.mapper.GradeMapper;
import com.capgemini.gradebook.persistence.entity.GradeEntity;
import com.capgemini.gradebook.persistence.entity.GradeType;
import com.capgemini.gradebook.persistence.entity.StudentEntity;
import com.capgemini.gradebook.persistence.entity.SubjectEntity;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.persistence.repo.GradeRepo;
import com.capgemini.gradebook.persistence.repo.StudentRepo;
import com.capgemini.gradebook.persistence.repo.SubjectRepo;
import com.capgemini.gradebook.persistence.repo.TeacherRepo;
import com.capgemini.gradebook.service.GradeService;
import com.capgemini.gradebook.service.exception.BadRequestException;
import com.capgemini.gradebook.service.exception.NotFoundException;

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
    return result.map(r -> GradeMapper.mapToETO(r)).orElseThrow(() -> new NotFoundException("Grade not found"));
  }

  @Override
  public GradeEto save(GradeEto newGrade) {

    if (newGrade.getId() != null) {
      throw new BadRequestException("Grade with such id already exists");
    }

    GradeEntity gradeEntity = GradeMapper.mapToEntity(newGrade);
    if (newGrade.getTeacherId() == null) {
      throw new NotFoundException("Grade has no teacher id");
    } else {
      Optional<TeacherEntity> teacherEntity = this.teacherRepository.findById(newGrade.getTeacherId());
      if(teacherEntity.isPresent()) {
        gradeEntity.setTeacherEntity(teacherEntity.get());
      } else {
        throw new NotFoundException("There is no teacher with such id in db");
      }
    }

    if (newGrade.getStudentId() == null) {
      throw new NotFoundException("Grade has no student id");
    } else {
      Optional<StudentEntity> studentEntity = this.studentRepository.findById(newGrade.getStudentId());
      if(studentEntity.isPresent()) {
        gradeEntity.setStudentEntity(studentEntity.get());
      } else {
        throw new NotFoundException("There is no student with such id in db");
      }
    }

    if (newGrade.getSubjectId() == null) {
      throw new NotFoundException("Grade has no subject id");
    } else {
      Optional<SubjectEntity> subjectEntity = this.subjectRepository.findById(newGrade.getSubjectId());
      if(subjectEntity.isPresent()) {
        gradeEntity.setSubjectEntity(subjectEntity.get());
      } else {
        throw new NotFoundException("There is no subject with such id in db");
      }
    }

    if ((newGrade.getValue() == 6 || newGrade.getValue() == 1) && newGrade.getComment().isEmpty()) {
      throw new BadRequestException("Grades 6 and 1 should be accompanied by the comment");
    }

    gradeEntity = this.gradeRepository.save(gradeEntity);
    return GradeMapper.mapToETO(gradeEntity);
  }

  @Override
  public GradeEto updateGrade(GradeEto gradeEto, Long gradeId) {
    int gradeValue = gradeEto.getValue();
    BigDecimal gradeWeight = gradeEto.getWeight();
    GradeType gradeType = gradeEto.getGradeType();
    String gradeComment = gradeEto.getComment();
    Date gradeDate = gradeEto.getDateOfGrade();

    TeacherEntity teacher = teacherRepository.findById(gradeEto.getTeacherId()).get();
    SubjectEntity subject = subjectRepository.findById(gradeEto.getSubjectId()).get();
    StudentEntity student = studentRepository.findById(gradeEto.getStudentId()).get();

    GradeEntity grade = this.gradeRepository.findById(gradeId).orElseThrow(() -> new IllegalStateException(
        "Grade with id " + gradeEto.getId() + " doesn't exist"
    ));

    if(gradeValue > 0 && !Objects.equals(grade.getValue(), gradeValue)) {
      grade.setValue(gradeValue);
    }

//    if(gradeWeight > 0 && !Objects.equals(grade.getWeight(), gradeWeight)) {
//      grade.setWeight(gradeWeight);
//    }

    if(gradeType != null && gradeType.toString().length() > 0 && !Objects.equals(grade.getGradeType(), gradeType)) {
      grade.setGradeType(gradeType);
    }

    if(gradeComment != null && gradeComment.length() > 0 && !Objects.equals(grade.getComment(), gradeComment)) {
      grade.setComment(gradeComment);
    }

    if (gradeDate != null && !Objects.equals(grade.getDateOfGrade(), gradeDate)) {
      grade.setDateOfGrade(gradeDate);
    }

    if(teacher != null && !Objects.equals(grade.getTeacherEntity(), teacher)) {
      grade.setTeacherEntity(teacher);
    }

    if(subject != null && !Objects.equals(grade.getSubjectEntity(), subject)) {
      grade.setSubjectEntity(subject);
    }

    if(student != null && !Objects.equals(grade.getStudentEntity(), student)) {
      grade.setStudentEntity(student);
    }

    return GradeMapper.mapToETO(grade);
  }

  @Override
  public void delete(Long id) {
    if (!gradeRepository.existsById(id)) {
      throw new NotFoundException("Grade with id " + id + " does not exist");
    }

    this.gradeRepository.deleteById(id);}
}
