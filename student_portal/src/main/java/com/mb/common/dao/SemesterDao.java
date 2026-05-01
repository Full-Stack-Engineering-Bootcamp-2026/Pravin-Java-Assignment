
package com.mb.common.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.mb.common.entities.Semester;

public interface SemesterDao {

   Semester findById(Long id);

   boolean existsByStudentIdAndSemesterNumber(Long studentId, Integer semesterNumber);

   Semester save(Semester result);

   List<Semester> findByStudentId(Long studentId);

   List<Semester> findBySemesterOrderByPercentageDesc(Integer semester);

   List<Semester> findLowPerformance(Double minPercentage);
}
