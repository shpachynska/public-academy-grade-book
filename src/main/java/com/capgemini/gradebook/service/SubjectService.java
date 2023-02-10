package com.capgemini.gradebook.service;

import java.util.List;

import com.capgemini.gradebook.domain.SubjectEto;

public interface SubjectService {
  List<SubjectEto> findAllSubjects();

  SubjectEto findSubjectById(Long id);

  SubjectEto save(SubjectEto newSubject);

  void delete(Long id);
}
