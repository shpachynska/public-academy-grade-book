package com.capgemini.gradebook.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.gradebook.domain.GradeEto;
import com.capgemini.gradebook.persistence.entity.GradeEntity;

public final class GradeMapper {
  public static final GradeEto mapToETO(GradeEntity entity) {
    GradeEto grade = new GradeEto();
    grade.setValue(entity.getValue());
    grade.setWeight(entity.getWeight());
    grade.setGradeType(entity.getGradeType());
    grade.setComment(entity.getComment());
    grade.setDateOfGrade(entity.getDateOfGrade());
    grade.setStudentId(entity.getStudentEntity().getId());
    grade.setSubjectId(entity.getSubjectEntity().getId());
    grade.setTeacherId(entity.getTeacherEntity().getId());
    return grade;
  }

  public static final GradeEntity mapToEntity(GradeEto gradeTo) {

    GradeEntity entity = new GradeEntity();
    entity.setId(gradeTo.getId());
    entity.setVersion(gradeTo.getVersion());
    entity.setCreateDate(gradeTo.getCreateDate());
    entity.setUpdateDate(gradeTo.getUpdateDate());
    entity.setComment(gradeTo.getComment());
    entity.setValue(gradeTo.getValue());
    entity.setWeight(gradeTo.getWeight());
    entity.setGradeType(gradeTo.getGradeType());
    entity.setDateOfGrade(gradeTo.getDateOfGrade());
    return entity;
  }

  public static final List<GradeEto> mapToETOList(List<GradeEntity> entities) {
    return entities.stream().map(e -> mapToETO(e)).collect(Collectors.toList());
  }

  public static final List<GradeEntity> mapToEntityList(List<GradeEto> tos) {
    return tos.stream().map(t -> mapToEntity(t)).collect(Collectors.toList());
  }

}
