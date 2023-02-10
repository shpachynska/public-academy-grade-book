package com.capgemini.gradebook.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STUDENT")
public class StudentEntity extends AbstractEntity {

  private String lastName;
  private String firstName;
  private Integer age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ClassYearId")
  @NotNull
  private ClassYearEntity classYearEntity;

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

   /**
   * @return lastName
   */
  public String getLastName() {

    return this.lastName;
  }

  /**
   * @param lastName the new value.
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  /**
   * @return firstName
   */
  public String getFirstName() {

    return this.firstName;
  }

  /**
   * @param firstName the new value.
   */
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  /**
   * @return age
   */
  public Integer getAge() {

    return this.age;
  }

  /**
   * @param age the new value.
   */
  public void setAge(Integer age) {

    this.age = age;
  }
}
