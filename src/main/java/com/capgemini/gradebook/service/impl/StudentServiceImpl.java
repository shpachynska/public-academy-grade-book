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
import com.capgemini.gradebook.service.exception.NotFoundException;

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
    return result.map(r -> StudentMapper.mapToETO(r)).orElseThrow( () -> new NotFoundException("Student not found"));
  }

  @Override
  public StudentEto save(StudentEto newStudent) {

    if (newStudent.getId() != null) {
      throw new BadRequestException("Student with such id already exists");
    }

    StudentEntity studentEntity = StudentMapper.mapToEntity(newStudent);
    if(newStudent.getClassYearId() == null) {
      throw new NotFoundException("Student has no class year");
    } else {
      Optional<ClassYearEntity> classYearEntity = this.classYearRepository.findById(newStudent.getClassYearId());
      if(classYearEntity.isPresent()) {
        studentEntity.setClassYearEntity(classYearEntity.get());
      } else {
        throw new NotFoundException("There is no class year with such id in db");
      }
    }
    studentEntity = this.studentRepository.save(studentEntity);
    return StudentMapper.mapToETO(studentEntity);
  }

  @Override
  public StudentEto updateStudent(StudentEto studentEto, Long studentId) {

    if (!studentRepository.existsById(studentId)) {
      throw new NotFoundException("Student with id " + studentId + " doesn't exist");
    }

    StudentEntity existingStudent = studentRepository.getOne(studentId);
    ClassYearEntity classYear = classYearRepository.findById(studentEto.getClassYearId())
            .orElseThrow(() -> new NotFoundException("There is no such class year in database"));

    studentEto.setVersion(existingStudent.getVersion());
    studentEto.setId(studentId);

    StudentEntity studentToUpdate = StudentMapper.mapToEntity(studentEto);
    studentToUpdate.setClassYearEntity(classYear);

    return StudentMapper.mapToETO(studentRepository.save(studentToUpdate));
  }

  @Override
  public void delete(Long id) {
    if (!studentRepository.existsById(id)) {
      throw new NotFoundException("Student with id " + id + " does not exist");
    }

    this.studentRepository.deleteById(id);
  }
}
