package com.capgemini.gradebook.domain;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.capgemini.gradebook.persistence.entity.GradeType;
import com.capgemini.gradebook.persistence.entity.StudentEntity;
import com.capgemini.gradebook.persistence.entity.SubjectEntity;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;

public class GradeEto extends AbstractEto {

  private TeacherEntity teacherEntity;
  private SubjectEntity subjectEntity;
  private StudentEntity studentEntity;
  private Integer value;
  private Integer weight;

  @Enumerated(EnumType.STRING)
  private GradeType gradeType;

  private String comment;
  private Date dateOfGrade;

  /**
   * @return teacherEntity
   */
  public TeacherEntity getTeacherEntity() {

    return this.teacherEntity;
  }

  /**
   * @param teacherEntity the new value.
   */
  public void setTeacherEntity(TeacherEntity teacherEntity) {

    this.teacherEntity = teacherEntity;
  }

  /**
   * @return subjectEntity
   */
  public SubjectEntity getSubjectEntity() {

    return this.subjectEntity;
  }

  /**
   * @param subjectEntity the new value.
   */
  public void setSubjectEntity(SubjectEntity subjectEntity) {

    this.subjectEntity = subjectEntity;
  }

  /**
   * @return studentEntity
   */
  public StudentEntity getStudentEntity() {

    return this.studentEntity;
  }

  /**
   * @param studentEntity the new value.
   */
  public void setStudentEntity(StudentEntity studentEntity) {

    this.studentEntity = studentEntity;
  }

  /**
   * @return value
   */
  public Integer getValue() {

    return this.value;
  }

  /**
   * @param value the new value.
   */
  public void setValue(Integer value) {

    this.value = value;
  }

  /**
   * @return weight
   */
  public Integer getWeight() {

    return this.weight;
  }

  /**
   * @param weight the new value.
   */
  public void setWeight(Integer weight) {

    this.weight = weight;
  }

  /**
   * @return gradeType
   */
  public GradeType getGradeType() {

    return this.gradeType;
  }

  /**
   * @param gradeType the new value.
   */
  public void setGradeType(GradeType gradeType) {

    this.gradeType = gradeType;
  }

  /**
   * @return comment
   */
  public String getComment() {

    return this.comment;
  }

  /**
   * @param comment the new value.
   */
  public void setComment(String comment) {

    this.comment = comment;
  }

  /**
   * @return dateOfGrade
   */
  public Date getDateOfGrade() {

    return this.dateOfGrade;
  }

  /**
   * @param dateOfGrade the new value.
   */
  public void setDateOfGrade(Date dateOfGrade) {

    this.dateOfGrade = dateOfGrade;
  }
}