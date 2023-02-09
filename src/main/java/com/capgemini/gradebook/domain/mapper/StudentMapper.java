package com.capgemini.gradebook.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.gradebook.domain.StudentEto;
import com.capgemini.gradebook.domain.TeacherEto;
import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.StudentEntity;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.service.ClassService;

public final class StudentMapper {

  public static final StudentEto mapToETO(StudentEntity entity){

    StudentEto student = new StudentEto();
    student.setId(entity.getId());
    student.setVersion(entity.getVersion());
    student.setCreateDate(entity.getCreateDate());
    student.setUpdateDate(entity.getUpdateDate());
    student.setFirstName(entity.getFirstName());
    student.setLastName(entity.getLastName());
    student.setAge(entity.getAge());
    student.setClassYearId(entity.getClassYearEntity().getId());
    return student;
  }

  //

  public static final StudentEntity mapToEntity(StudentEto studentTo){

    StudentEntity entity = new StudentEntity();
    entity.setId(studentTo.getId());
    entity.setVersion(studentTo.getVersion());
    entity.setCreateDate(studentTo.getCreateDate());
    entity.setUpdateDate(studentTo.getUpdateDate());
    entity.setFirstName(studentTo.getFirstName());
    entity.setLastName(studentTo.getLastName());
    entity.setAge(studentTo.getAge());
    return entity;
  }

  public static final List<StudentEto> mapToETOList(List<StudentEntity> entities){
    return entities.stream().map(e -> mapToETO(e)).collect(Collectors.toList());
  }

  public static final List<StudentEntity> mapToEntityList(List<StudentEto> tos){
    return tos.stream().map(t -> mapToEntity(t)).collect(Collectors.toList());
  }

}
