package com.capgemini.gradebook.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.SubjectType;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;

public class SubjectEto extends AbstractEto {
  private String name;
  private Long teacherId;
  private Long classYearId;

  @Enumerated(EnumType.STRING)
  private SubjectType subjectType;


   /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name the new value.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return subjectType
   */
  public SubjectType getSubjectType() {

    return this.subjectType;
  }

  /**
   * @param subjectType the new value.
   */
  public void setSubjectType(SubjectType subjectType) {

    this.subjectType = subjectType;
  }

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
   * @return classYearId
   */
  public Long getClassYearId() {

    return this.classYearId;
  }

  /**
   * @param classYearId the new value.
   */
  public void setClassYearId(Long classYearId) {

    this.classYearId = classYearId;
  }
}
