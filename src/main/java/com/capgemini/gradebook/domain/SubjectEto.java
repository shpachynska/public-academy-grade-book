package com.capgemini.gradebook.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.SubjectType;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;

public class SubjectEto extends AbstractEto {
  private String name;

  @Enumerated(EnumType.STRING)
  private SubjectType subjectType;

  private TeacherEntity teacherEntity;

  private ClassYearEntity classYearEntity;

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
   * @return classYearEntity
   */
  public ClassYearEntity getClassYearEntity() {

    return this.classYearEntity;
  }

  /**
   * @param classYearEntity the new value.
   */
  public void setClassYearEntity(ClassYearEntity classYearEntity) {

    this.classYearEntity = classYearEntity;
  }
}
