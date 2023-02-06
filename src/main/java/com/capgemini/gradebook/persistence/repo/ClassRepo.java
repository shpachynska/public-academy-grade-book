package com.capgemini.gradebook.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.gradebook.persistence.entity.ClassEntity;

public interface ClassRepo extends JpaRepository<ClassEntity, Long> {
}
