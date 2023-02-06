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
}
