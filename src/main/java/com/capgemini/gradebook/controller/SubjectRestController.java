package com.capgemini.gradebook.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.gradebook.domain.SubjectEto;
import com.capgemini.gradebook.service.SubjectService;

@RestController
@RequestMapping("/rest")
public class SubjectRestController {
  private final SubjectService subjectService;

  @Autowired
  public SubjectRestController (final SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @GetMapping("/subjects")
  public ResponseEntity<List<SubjectEto>> findAllSubjects() {
    final List<SubjectEto> allSubjects = this.subjectService.findAllSubjects();
    return ResponseEntity.ok().body(allSubjects);
  }

  @GetMapping("/subjects/{id}")
  public ResponseEntity<SubjectEto> findSubjectsById(@PathVariable("id") final Long id) {

    final SubjectEto subject = this.subjectService.findSubjectById(id);
    return ResponseEntity.ok().body(subject);
  }

  @PostMapping("/subjects")
  public SubjectEto addSubject(@RequestBody SubjectEto newSubject) {

    return subjectService.save(newSubject);
  }

  @PutMapping("/subjects/{id}")
  public void upsertSubject(@RequestBody SubjectEto subject, @PathVariable Long id) {

    subjectService.updateSubject(subject, id);
  }

  @DeleteMapping("/subjects/{id}")
  void deleteSubject(@PathVariable Long id) {
    subjectService.delete(id);
  }
}
