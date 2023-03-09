package com.capgemini.gradebook.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.gradebook.persistence.entity.StudentEntity;

public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
}
