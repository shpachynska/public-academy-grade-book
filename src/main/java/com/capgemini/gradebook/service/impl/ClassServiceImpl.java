package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.ClassEto;
import com.capgemini.gradebook.domain.mapper.ClassMapper;
import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.repo.ClassRepo;
import com.capgemini.gradebook.service.ClassService;
import com.capgemini.gradebook.service.exception.BadRequestException;
import com.capgemini.gradebook.service.exception.NotFoundException;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

  private final ClassRepo classRepository;

  @Autowired
  public ClassServiceImpl(final ClassRepo classRepository) {

    this.classRepository = classRepository;
  }

  @Override
  public List<ClassEto> findAllClasses() {

    return ClassMapper.mapToETOList(this.classRepository.findAll());
  }

  @Override
  public ClassEto save(ClassEto newClass) {

    if (newClass.getId() != null) {
      throw new BadRequestException("Class with such id already exists");
    }

    ClassYearEntity classYearEntity = ClassMapper.mapToEntity(newClass);
    classYearEntity = this.classRepository.save(classYearEntity);
    return ClassMapper.mapToETO(classYearEntity);
  }

  @Override
  public ClassEto updateClassYear(ClassEto classEto, Long classId) {
    int classYearClassLevel = (int) classEto.getClassLevel();
    String classYearClassName = classEto.getClassName();
    String classYearClassYear = classEto.getClassYear();


    ClassYearEntity classYear =
        this.classRepository.findById(classId).orElseThrow(() -> new IllegalStateException(
            "Class with" +
                " id " + classId + " doesn't exist"));

    if(classYearClassLevel > 0 && !Objects.equals(classYear.getClassYear(),
        classYearClassLevel)) {
      classYear.setClassLevel(classYearClassLevel);
    }

    if(classYearClassName != null && classYearClassName.length() > 0 && !Objects.equals(classYear.getClassName(),
        classYearClassName)) {
      classYear.setClassName(classYearClassName);
    }

    if(classYearClassYear != null && classYearClassYear.length() > 0 && !Objects.equals(classYear.getClassYear(),
        classYearClassYear)) {
      classYear.setClassYear(classYearClassYear);
    }
    return ClassMapper.mapToETO(classYear);
  }


  @Override
  public ClassEto findClassById(Long id) {

    final Optional<ClassYearEntity> result = this.classRepository.findById(id);
    return result.map(r -> ClassMapper.mapToETO(r)).orElseThrow( ()-> new NotFoundException("Class not found"));
  }

  @Override
  public void delete(Long id) {
    if(!classRepository.existsById(id)) {
      throw new NotFoundException("Classyear with id " + id + " does not exist");
    }

    this.classRepository.deleteById(id);
  }
}
