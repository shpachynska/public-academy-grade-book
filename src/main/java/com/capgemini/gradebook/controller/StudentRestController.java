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
import com.capgemini.gradebook.domain.StudentEto;
import com.capgemini.gradebook.service.StudentService;

@RestController
@RequestMapping("/rest")
public class StudentRestController {
  private final StudentService studentService;

  @Autowired
  public StudentRestController(final StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/students")
  public ResponseEntity<List<StudentEto>> findAllStudents() {
    final List<StudentEto> allStudents = this.studentService.findAllStudents();
    return ResponseEntity.ok().body(allStudents);
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<StudentEto> findStudentById(@PathVariable("id") final Long id) {

    final StudentEto student = this.studentService.findStudentById(id);
    return ResponseEntity.ok().body(student);
  }

  @PostMapping("/students")
  public StudentEto addStudent(@RequestBody StudentEto newStudent) {
    return studentService.save(newStudent);
  }

  @PutMapping("/students/{id}")
  StudentEto upsertStudent(@RequestBody StudentEto student) {
    return studentService.save(student);
  }

  @DeleteMapping("/students/{id}")
  void deleteStudent(@PathVariable Long id) {
    studentService.delete(id);
  }
}
