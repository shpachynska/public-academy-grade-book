package com.capgemini.gradebook.service;

import java.util.List;

import com.capgemini.gradebook.domain.GradeEto;

public interface GradeService {

  List<GradeEto> findAllGrades();

  GradeEto findGradeById(Long id);

  GradeEto save(GradeEto newGrade);

  void delete(Long id);
}
