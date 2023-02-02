package com.capgemini.gradebook.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class StudentEntity extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private ClassEntity classEntity;

  private String lastName;
  private String firstName;
  private Integer age;

  /**
   * @return classEntity
   */
  public ClassEntity getClassEntity() {

    return this.classEntity;
  }

  /**
   * @param classEntity the new value.
   */
  public void setClassEntity(ClassEntity classEntity) {

    this.classEntity = classEntity;
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
