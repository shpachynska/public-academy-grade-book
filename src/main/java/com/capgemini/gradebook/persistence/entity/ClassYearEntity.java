package com.capgemini.gradebook.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLASS_YEAR")
public class ClassYearEntity extends AbstractEntity {

 @OneToMany(mappedBy = "classYearEntity")
  private List<StudentEntity> studentEntities;

  private Number classLevel;
  private String className;
  private String classYear;

  /**
   * @return students
   */
  public List<StudentEntity> getStudents() {

    return this.studentEntities;
  }

  /**
   * @param studentEntities the new value.
   */
  public void setStudents(List<StudentEntity> studentEntities) {

    this.studentEntities = studentEntities;
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
