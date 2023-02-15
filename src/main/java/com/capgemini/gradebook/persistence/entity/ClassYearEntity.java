package com.capgemini.gradebook.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CLASS_YEAR")
public class ClassYearEntity extends AbstractEntity {

  @OneToMany(mappedBy = "classYearEntity")
  private List<StudentEntity> studentEntities;

  @OneToMany(mappedBy = "classYearEntity")
  private List<SubjectEntity> subjectEntities;

  @NotNull
  private Number classLevel;

  @NotNull
  @Column(length = 2)
  private String className;

  @NotNull
  @Column(length = 16)
  private String classYear;

  /**
   * @return students
   */
  public List<StudentEntity> getStudentEntities() {

    return this.studentEntities;
  }

  /**
   * @param studentEntities the new value.
   */
  public void setStudentEntities(List<StudentEntity> studentEntities) {

    this.studentEntities = studentEntities;
  }

  /**
   * @return subjectEntities
   */
  public List<SubjectEntity> getSubjectEntities() {

    return this.subjectEntities;
  }

  /**
   * @param subjectEntities the new value.
   */
  public void setSubjectEntities(List<SubjectEntity> subjectEntities) {

    this.subjectEntities = subjectEntities;
  }

  /**
   * @return classLevel
   */
  public Number getClassLevel() {

    return this.classLevel;
  }

  /**
   * @param classLevel the new value.
   */
  public void setClassLevel(Number classLevel) {

    this.classLevel = classLevel;
  }

  /**
   * @return className
   */
  public String getClassName() {

    return this.className;
  }

  /**
   * @param className the new value.
   */
  public void setClassName(String className) {

    this.className = className;
  }

  /**
   * @return classYear
   */
  public String getClassYear() {

    return this.classYear;
  }

  /**
   * @param classYear the new value.
   */
  public void setClassYear(String classYear) {

    this.classYear = classYear;
  }
}
