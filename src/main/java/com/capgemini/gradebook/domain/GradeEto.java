package com.capgemini.gradebook.domain;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.capgemini.gradebook.persistence.entity.GradeType;

public class GradeEto extends AbstractEto {

  private Long teacherId;
  private Long subjectId;
  private Long studentId;
  private Integer value;
  private Integer weight;

  @Enumerated(EnumType.STRING)
  private GradeType gradeType;

  private String comment;
  private Date dateOfGrade;

  /**
   * @return teacherId
   */
  public Long getTeacherId() {

    return this.teacherId;
  }

  /**
   * @param teacherId the new value.
   */
  public void setTeacherId(Long teacherId) {

    this.teacherId = teacherId;
  }

  /**
   * @return subjectId
   */
  public Long getSubjectId() {

    return this.subjectId;
  }

  /**
   * @param subjectId the new value.
   */
  public void setSubjectId(Long subjectId) {

    this.subjectId = subjectId;
  }

  /**
   * @return studentId
   */
  public Long getStudentId() {

    return this.studentId;
  }

  /**
   * @param studentId the new value.
   */
  public void setStudentId(Long studentId) {

    this.studentId = studentId;
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
