package com.capgemini.gradebook.service;

import com.capgemini.gradebook.domain.TeacherEto;

import java.util.List;

public interface TeacherService {

	List<TeacherEto> findAllTeachers();

	List<TeacherEto> findTeacherByLastName(final String lastname) ;

	TeacherEto findTeacherById(Long id);

	TeacherEto save(TeacherEto newTeacher);

	TeacherEto updateTeacher(TeacherEto teacherEto, Long id);

	void delete(Long id);
}
