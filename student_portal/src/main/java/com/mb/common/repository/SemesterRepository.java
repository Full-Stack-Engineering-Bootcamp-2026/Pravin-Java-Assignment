package com.mb.common.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.mb.common.entities.Semester;
import com.mb.common.entities.Student;

public interface SemesterRepository extends JpaRepository<Semester, Long>, JpaSpecificationExecutor<Student> {
  boolean existsByStudentIdAndSemesterNumber(Long studentId, Integer semesterNumber);

  List<Semester> findByStudentId(Long studentId);

  @EntityGraph(attributePaths = { "student" })
  List<Semester> findBySemesterNumberOrderByPercentageDesc(Integer semesterNumber);

  @EntityGraph(attributePaths = { "student" })
  List<Semester> findByPercentageLessThan(Double minPercentage);

}