package com.capgemini.gradebook.service;

import java.util.List;

import com.capgemini.gradebook.domain.ClassEto;
import com.capgemini.gradebook.domain.TeacherEto;

public interface ClassService {
  List<ClassEto> findAllClasses();

  ClassEto save(ClassEto newClass);

  ClassEto updateClassYear(ClassEto classEto, Long id);

  ClassEto findClassById(Long id);

  void delete(Long id);
}
