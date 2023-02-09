package com.capgemini.gradebook.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.gradebook.persistence.entity.ClassYearEntity;

public interface ClassRepo extends JpaRepository<ClassYearEntity, Long> {
}
