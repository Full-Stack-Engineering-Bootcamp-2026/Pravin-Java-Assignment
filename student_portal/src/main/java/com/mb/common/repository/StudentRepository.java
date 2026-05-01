package com.mb.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mb.common.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

      Optional<Student> findByAuth0Id(String auth0Id);

      Optional<Student> findByEnrollmentNumber(String enrollmentNo);

      boolean existsByAuth0Id(String auth0Id);

      boolean existsByEmail(String email);

      boolean existsByEnrollmentNumber(String enrollmentNumber);

      List<Student> findAllByIsActiveTrue();

}