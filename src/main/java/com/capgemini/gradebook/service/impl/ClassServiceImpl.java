package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.ClassEto;
import com.capgemini.gradebook.domain.TeacherEto;
import com.capgemini.gradebook.domain.mapper.ClassMapper;
import com.capgemini.gradebook.domain.mapper.TeacherMapper;
import com.capgemini.gradebook.persistence.entity.ClassYearEntity;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.persistence.repo.ClassRepo;
import com.capgemini.gradebook.service.ClassService;

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

    ClassYearEntity classYearEntity = ClassMapper.mapToEntity(newClass);
    classYearEntity = this.classRepository.save(classYearEntity);
    return ClassMapper.mapToETO(classYearEntity);
  }

  @Override
  public ClassEto findClassById(Long id) {

    final Optional<ClassYearEntity> result = this.classRepository.findById(id);
    return result.map(r -> ClassMapper.mapToETO(r)).orElseThrow( ()-> new RuntimeException("Class not found " +
        "exception"));
  }

  @Override
  public void delete(Long id) {

    this.classRepository.deleteById(id);

  }
}
