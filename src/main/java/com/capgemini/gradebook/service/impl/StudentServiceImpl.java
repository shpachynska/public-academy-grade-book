package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.StudentEto;
import com.capgemini.gradebook.domain.mapper.StudentMapper;
import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.StudentEntity;
import com.capgemini.gradebook.persistence.repo.ClassRepo;
import com.capgemini.gradebook.persistence.repo.StudentRepo;
import com.capgemini.gradebook.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  private final StudentRepo studentRepository;

  private final ClassRepo classYearRepository;

  @Autowired
  public StudentServiceImpl(final StudentRepo studentRepository, ClassRepo classYearRepository) {
    this.studentRepository = studentRepository;
    this.classYearRepository = classYearRepository;
  }

  @Override
  public List<StudentEto> findAllStudents() {
    return StudentMapper.mapToETOList(this.studentRepository.findAll());
  }

  @Override
  public StudentEto findStudentById(Long id) {
    final Optional<StudentEntity> result = this.studentRepository.findById(id);
    return result.map(r -> StudentMapper.mapToETO(r)).orElseThrow( () -> new RuntimeException("Student not found " +
        "exception"));
  }

  @Override
  public StudentEto save(StudentEto newStudent) {

//    if(newStudent.getId() != null) {
//      boolean studentAlreadyPresent = true;
//      try {
//        this.findStudentById(newStudent.getId());
//      } catch (RuntimeException e) {
//        studentAlreadyPresent = false;
//      }
//      if(studentAlreadyPresent) {
//        throw new RuntimeException("Student with this id is already in the database");
//      }
//    }

    StudentEntity studentEntity = StudentMapper.mapToEntity(newStudent);
//    if(newStudent.getClassYearId() == null) {
//      throw new RuntimeException("student has no class year");
//    } else {
    Optional<ClassYearEntity> classYearEntity = this.classYearRepository.findById(newStudent.getClassYearId());
//    if(classYearEntity.isPresent()) {
      studentEntity.setClassYearEntity(classYearEntity.get());
//    } else {
//      throw new RuntimeException("There is no class year with such id in db");
//    }
//    }
    studentEntity = this.studentRepository.save(studentEntity);
    return StudentMapper.mapToETO(studentEntity);
  }

  @Override
  public void delete(Long id) {
    this.studentRepository.deleteById(id);
  }
}
