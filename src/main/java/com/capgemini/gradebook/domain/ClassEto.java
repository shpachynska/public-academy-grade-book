package com.capgemini.gradebook.domain;

public class ClassEto extends AbstractEto {
  private Number classLevel;
  private String className;
  private String classYear;

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

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof ClassEto)) {
      return false;
    }

    ClassEto classEto = (ClassEto) o;

    if (this.classLevel != null ? !this.classLevel.equals(classEto.classLevel) : classEto.classLevel != null) {
      return false;
    }
    if (this.className != null ? !this.className.equals(classEto.className) : classEto.className != null) {
      return false;
    }
    return this.classYear != null ? this.classYear.equals(classEto.classYear) : classEto.classYear == null;
  }

  @Override
  public int hashCode() {

    int result = this.classLevel != null ? this.classLevel.hashCode() : 0;
    result = 31 * result + (this.className != null ? this.className.hashCode() : 0);
    result = 31 * result + (this.classYear != null ? this.classYear.hashCode() : 0);
    return result;
  }
}
