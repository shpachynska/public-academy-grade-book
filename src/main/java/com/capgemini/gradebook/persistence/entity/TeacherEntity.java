package com.capgemini.gradebook.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TEACHER")
public class TeacherEntity extends AbstractEntity {

//  @Column(length = 64)
  @Size(max = 64)
  private String firstName;

  @Column(length = 64)
  private String lastName;

   //TODO IMPLEMENT: after creating subjectEntity and other subject classes uncomment the lines below, making necessary
  // adjustments; then generate getters and setters and fix the mappers
  @OneToMany(mappedBy = "teacherEntity", fetch = FetchType.LAZY)
  private List<SubjectEntity> subjectEntityList;

  @OneToMany(mappedBy = "teacherEntity", fetch = FetchType.LAZY)
  private List<GradeEntity> gradeEntityList;


  //TODO IMPLEMENT: create @OneToMany with mappedBy to subjects after you create a base model

  /**
   * @return subjectEntityList
   */
  public List<SubjectEntity> getSubjectEntityList() {

    return this.subjectEntityList;
  }

  /**
   * @param subjectEntityList the new value.
   */
  public void setSubjectEntityList(List<SubjectEntity> subjectEntityList) {

    this.subjectEntityList = subjectEntityList;
  }

  /**
   * @return gradeEntityList
   */
  public List<GradeEntity> getGradeEntityList() {

    return this.gradeEntityList;
  }

  /**
   * @param gradeEntityList the new value.
   */
  public void setGradeEntityList(List<GradeEntity> gradeEntityList) {

    this.gradeEntityList = gradeEntityList;
  }

  public String getFirstName() {

    return this.firstName;
  }


  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }


  public String getLastName() {

    return this.lastName;
  }


  public void setLastName(String lastName) {

    this.lastName = lastName;
  }
}
