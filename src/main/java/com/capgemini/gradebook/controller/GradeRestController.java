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

import com.capgemini.gradebook.domain.GradeEto;
import com.capgemini.gradebook.service.GradeService;

/**
 *
 */
@RestController
@RequestMapping("/rest")
public class GradeRestController {
  private final GradeService gradeService;

  @Autowired
  public GradeRestController (final GradeService gradeService) { this.gradeService = gradeService; }

  @GetMapping("/grades")
  public ResponseEntity<List<GradeEto>> findAllGrades() {
    final List<GradeEto> allGrades = this.gradeService.findAllGrades();
    return ResponseEntity.ok().body(allGrades);
  }

  @GetMapping("/grades/{id}")
  public ResponseEntity<GradeEto> findGradeById(@PathVariable("id") final Long id) {

    final GradeEto grade = this.gradeService.findGradeById(id);
    return ResponseEntity.ok().body(grade);
  }

  @PostMapping("/grades")
  public GradeEto addGrade(@RequestBody GradeEto newGrade) { return gradeService.save(newGrade);}

  @PutMapping("/grades/{id}")
  GradeEto upsertGrade(@RequestBody GradeEto grade, @PathVariable Long id) {

    return gradeService.updateGrade(grade, id);
  }

  @DeleteMapping("/grades/{id}")
  void deleteGrade(@PathVariable Long id) {gradeService.delete(id);}

}
