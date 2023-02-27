package com.capgemini.gradebook.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.gradebook.domain.TeacherEto;
import com.capgemini.gradebook.domain.mapper.TeacherMapper;
import com.capgemini.gradebook.persistence.entity.TeacherEntity;
import com.capgemini.gradebook.persistence.repo.TeacherRepo;
import com.capgemini.gradebook.service.TeacherService;
import com.capgemini.gradebook.service.exception.BadRequestException;
import com.capgemini.gradebook.service.exception.NotFoundException;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

  private final TeacherRepo teacherRepository;

  @Autowired
  public TeacherServiceImpl(final TeacherRepo teacherRepository) {

    this.teacherRepository = teacherRepository;
  }

  @Override
  public List<TeacherEto> findAllTeachers() {

    return TeacherMapper.mapToETOList(this.teacherRepository.findAll());
  }

  @Override
  public List<TeacherEto> findTeacherByLastName(final String lastname) {

    final List<TeacherEntity> teachers = this.teacherRepository.findTeacherByLastName(lastname);
    return teachers.stream().map(teacher -> TeacherMapper.mapToETO(teacher)).collect(Collectors.toList());
  }

  @Override
  public TeacherEto findTeacherById(Long id) {

    final Optional<TeacherEntity> result = this.teacherRepository.findById(id);
    return result.map(r -> TeacherMapper.mapToETO(r)).orElseThrow( ()-> new NotFoundException("Teacher not found"));
  }

  @Override
  public TeacherEto save(TeacherEto newTeacher) {

    if (newTeacher.getId() != null) {
      throw new BadRequestException("Teacher with such id already exists");
    }

    TeacherEntity teacherEntity = TeacherMapper.mapToEntity(newTeacher);
    teacherEntity = this.teacherRepository.save(teacherEntity);
    return TeacherMapper.mapToETO(teacherEntity);
  }

  @Override
  public TeacherEto updateTeacher(TeacherEto teacherEto, Long teacherId) {
    String teacherFirstName = teacherEto.getFirstName();
    String teacherLastName = teacherEto.getLastName();
    TeacherEntity teacher =
        this.teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalStateException(
        "Teacher with" +
        " id " + teacherId + " doesn't exist"));

    if(teacherFirstName != null && teacherFirstName.length() > 0 && !Objects.equals(teacher.getFirstName(), teacherFirstName)) {
      teacher.setFirstName(teacherFirstName);
    }

    if(teacherLastName != null && teacherLastName.length() > 0 && !Objects.equals(teacher.getLastName(), teacherLastName)) {
      teacher.setLastName(teacherLastName);
    }
    return TeacherMapper.mapToETO(teacher);
  }

  @Override
  public void delete(Long id) {
    if(!teacherRepository.existsById(id)) {
      throw new NotFoundException("Teacher with id " + id + " does not exists");
    }

    this.teacherRepository.deleteById(id);
  }
}
