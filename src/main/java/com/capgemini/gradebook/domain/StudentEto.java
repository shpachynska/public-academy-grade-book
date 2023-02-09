package com.capgemini.gradebook.domain;

import java.util.List;

public class StudentEto extends AbstractEto {
  private String lastName;
  private String firstName;
  private Integer age;

  private Long classYearId;
//  private List<Long> subjectList;

  /**
   * @return classGroupId
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
