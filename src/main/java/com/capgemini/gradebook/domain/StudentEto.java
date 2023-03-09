package com.capgemini.gradebook.domain;

import java.util.List;

public class StudentEto extends AbstractEto {
  private String lastName;
  private String firstName;
  private Integer age;

  private Long classYearId;
//  private List<Long> subjectList;

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

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof StudentEto)) {
      return false;
    }

    StudentEto that = (StudentEto) o;

    if (this.lastName != null ? !this.lastName.equals(that.lastName) : that.lastName != null) {
      return false;
    }
    if (this.firstName != null ? !this.firstName.equals(that.firstName) : that.firstName != null) {
      return false;
    }
    if (this.age != null ? !this.age.equals(that.age) : that.age != null) {
      return false;
    }
    return this.classYearId != null ? this.classYearId.equals(that.classYearId) : that.classYearId == null;
  }

  @Override
  public int hashCode() {

    int result = this.lastName != null ? this.lastName.hashCode() : 0;
    result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
    result = 31 * result + (this.age != null ? this.age.hashCode() : 0);
    result = 31 * result + (this.classYearId != null ? this.classYearId.hashCode() : 0);
    return result;
  }
}
