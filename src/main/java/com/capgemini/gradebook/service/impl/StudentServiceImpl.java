package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.StudentEto;
import com.capgemini.gradebook.domain.TeacherEto;
import com.capgemini.gradebook.domain.mapper.StudentMapper;
import com.capgemini.gradebook.domain.mapper.TeacherMapper;
import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.StudentEntity;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.persistence.repo.ClassRepo;
import com.capgemini.gradebook.persistence.repo.StudentRepo;
import com.capgemini.gradebook.service.StudentService;
import com.capgemini.gradebook.service.exception.BadRequestException;

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

    if (newStudent.getId() != null) {
      throw new BadRequestException("Teacher with such id already exists");
    }

    StudentEntity studentEntity = StudentMapper.mapToEntity(newStudent);
    if(newStudent.getClassYearId() == null) {
      throw new RuntimeException("student has no class year");
    } else {
      Optional<ClassYearEntity> classYearEntity = this.classYearRepository.findById(newStudent.getClassYearId());
      if(classYearEntity.isPresent()) {
        studentEntity.setClassYearEntity(classYearEntity.get());
      } else {
        throw new RuntimeException("There is no class year with such id in db");
      }
    }
    studentEntity = this.studentRepository.save(studentEntity);
    return StudentMapper.mapToETO(studentEntity);
  }

//  @Override
//  public StudentEto updateStudent(StudentEto studentEto) {
//    String studentFirstName = studentEto.getFirstName();
//    String studentLastName = studentEto.getLastName();
//    int studentAge = studentEto.getAge();
//    Long studentId = studentEto.getId();
//    ClassYearEntity classYear = classYearRepository.findById(studentEto.getClassYearId()).get();
//
//    StudentEntity student =
//        this.studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
//            "Student with" +
//                " id " + studentEto.getId() + " doesn't exist"));
//
//    if(studentFirstName != null && studentFirstName.length() > 0 && !Objects.equals(student.getFirstName(),
//        studentFirstName)) {
//      student.setFirstName(studentFirstName);
//    }
//
//    if(studentLastName != null && studentLastName.length() > 0 && !Objects.equals(student.getLastName(),
//        studentLastName)) {
//      student.setLastName(studentLastName);
//    }
//
//    if(studentAge > 0 && !Objects.equals(student.getAge(), studentAge)) {
//      student.setAge(studentAge);
//    }
//
//    if(classYear != null && > 0 && !Objects.equals(student.getAge(), studentAge)) {
//      student.setAge(studentAge);
//    }
//    return StudentMapper.mapToETO(student);
//  }

  @Override
  public void delete(Long id) {
    this.studentRepository.deleteById(id);
  }
}
