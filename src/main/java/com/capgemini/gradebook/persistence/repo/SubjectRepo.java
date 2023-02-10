package com.capgemini.gradebook.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.gradebook.persistence.entity.SubjectEntity;

public interface SubjectRepo extends JpaRepository<SubjectEntity, Long> {
}