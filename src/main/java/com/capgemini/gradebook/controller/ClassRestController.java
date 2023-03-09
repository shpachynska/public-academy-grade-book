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

import com.capgemini.gradebook.domain.ClassEto;
import com.capgemini.gradebook.domain.TeacherEto;
import com.capgemini.gradebook.service.ClassService;

@RestController
@RequestMapping("/rest")
public class ClassRestController {

  private final ClassService classService;

  @Autowired
  public ClassRestController(final ClassService classService) {

    this.classService = classService;
  }

  @GetMapping("/classes")
  public ResponseEntity<List<ClassEto>> findAllClasses() {
    final List<ClassEto> allClasses = this.classService.findAllClasses();
    return ResponseEntity.ok().body(allClasses);
  }

  @GetMapping("/classes/{id}")
  public ResponseEntity<ClassEto> findClassById(@PathVariable("id") final Long id) {

    final ClassEto classYear = this.classService.findClassById(id);
    return ResponseEntity.ok().body(classYear);
  }

  @PostMapping("/classes")
  public ClassEto addClass(@RequestBody ClassEto newClass) {
    //TODO IMPLEMENT: Post should always create a new entry in database. Add a new SERVICE method that ensures by
    // either throwing an exception if ID is present, or removing the given ID from ETO before save. Currently, this
    // method  may also be used to update existing entities
    return classService.save(newClass);
  }

  @PutMapping("/classes/{id}")
  ClassEto upsertGroup(@RequestBody ClassEto classYear, @PathVariable Long id) {
    return classService.updateClassYear(classYear, id);
  }

  @DeleteMapping("/classes/{id}")
  void deleteGroup(@PathVariable Long id) {classService.delete(id);}
}
