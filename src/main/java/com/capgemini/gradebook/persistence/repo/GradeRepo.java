package com.capgemini.gradebook.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.gradebook.persistence.entity.GradeEntity;

public interface GradeRepo extends JpaRepository<GradeEntity, Long> {
}
