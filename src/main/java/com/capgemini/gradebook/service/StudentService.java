package com.capgemini.gradebook.service;

import java.util.List;

import com.capgemini.gradebook.domain.StudentEto;

public interface StudentService {
  List<StudentEto> findAllStudents();

  StudentEto findStudentById(Long id);

  StudentEto save(StudentEto newStudent);

  void delete(Long id);
}
