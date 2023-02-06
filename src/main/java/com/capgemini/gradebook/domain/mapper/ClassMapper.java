package com.capgemini.gradebook.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.gradebook.domain.ClassEto;
import com.capgemini.gradebook.persistence.entity.ClassEntity;

public final class ClassMapper {

  public static final ClassEto mapToETO(ClassEntity entity) {
    ClassEto classT = new ClassEto();
    classT.setId(entity.getId());
    classT.setVersion(entity.getVersion());
    classT.setCreateDate(entity.getCreateDate());
    classT.setUpdateDate(entity.getUpdateDate());
    classT.setClassLevel(entity.getClassLevel());
    classT.setClassName(entity.getClassName());
    classT.setClassYear(entity.getClassYear());
    return classT;
  }

  public static final ClassEntity mapToEntity(ClassEto classTo){

    ClassEntity entity = new ClassEntity();
    entity.setId(classTo.getId());
    entity.setVersion(classTo.getVersion());
    entity.setCreateDate(classTo.getCreateDate());
    entity.setUpdateDate(classTo.getUpdateDate());
    entity.setClassLevel(classTo.getClassLevel());
    entity.setClassName(classTo.getClassName());
    entity.setClassYear(classTo.getClassYear());
    return entity;
  }

  public static final List<ClassEto> mapToETOList(List<ClassEntity> entities){
    return entities.stream().map(e -> mapToETO(e)).collect(Collectors.toList());
  }

  public static final List<ClassEntity> mapToEntityList(List<ClassEto> tos){
    return tos.stream().map(t -> mapToEntity(t)).collect(Collectors.toList());
  }
}
