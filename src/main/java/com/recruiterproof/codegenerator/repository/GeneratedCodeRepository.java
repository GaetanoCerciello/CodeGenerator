package com.recruiterproof.codegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruiterproof.codegenerator.entity.GeneratedCode;

@Repository
public interface GeneratedCodeRepository extends JpaRepository<GeneratedCode, Long> {
}
