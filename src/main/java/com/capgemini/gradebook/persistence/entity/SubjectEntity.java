package com.capgemini.gradebook.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "SUBJECT")
public class SubjectEntity extends AbstractEntity {

  //TODO IMPLEMENT: this should saved as a concatenation of subjectType and classyear value
  private String name;

  //TODO IMPLEMENT: no implementing here, but google why its better to persist enum values in the database in form of
  // strings or converted value instead of ordinal
  @Column(length = 64)
  @Enumerated(EnumType.STRING)
  private SubjectType subjectType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TeacherId")
  @NotNull
  private TeacherEntity teacherEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ClassYearId")
  @NotNull
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
