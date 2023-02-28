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

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof GradeEto)) {
      return false;
    }

    GradeEto gradeEto = (GradeEto) o;

    if (this.teacherId != null ? !this.teacherId.equals(gradeEto.teacherId) : gradeEto.teacherId != null) {
      return false;
    }
    if (this.subjectId != null ? !this.subjectId.equals(gradeEto.subjectId) : gradeEto.subjectId != null) {
      return false;
    }
    if (this.studentId != null ? !this.studentId.equals(gradeEto.studentId) : gradeEto.studentId != null) {
      return false;
    }
    if (this.value != null ? !this.value.equals(gradeEto.value) : gradeEto.value != null) {
      return false;
    }
    if (this.weight != null ? !this.weight.equals(gradeEto.weight) : gradeEto.weight != null) {
      return false;
    }
    if (this.gradeType != gradeEto.gradeType) {
      return false;
    }
    if (this.comment != null ? !this.comment.equals(gradeEto.comment) : gradeEto.comment != null) {
      return false;
    }
    return this.dateOfGrade != null ? this.dateOfGrade.equals(gradeEto.dateOfGrade) : gradeEto.dateOfGrade == null;
  }

  @Override
  public int hashCode() {

    int result = this.teacherId != null ? this.teacherId.hashCode() : 0;
    result = 31 * result + (this.subjectId != null ? this.subjectId.hashCode() : 0);
    result = 31 * result + (this.studentId != null ? this.studentId.hashCode() : 0);
    result = 31 * result + (this.value != null ? this.value.hashCode() : 0);
    result = 31 * result + (this.weight != null ? this.weight.hashCode() : 0);
    result = 31 * result + (this.gradeType != null ? this.gradeType.hashCode() : 0);
    result = 31 * result + (this.comment != null ? this.comment.hashCode() : 0);
    result = 31 * result + (this.dateOfGrade != null ? this.dateOfGrade.hashCode() : 0);
    return result;
  }
}
