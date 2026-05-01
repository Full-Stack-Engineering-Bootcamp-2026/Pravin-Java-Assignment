package com.mb.common.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mb.common.entities.Semester;
import com.mb.common.exception.ResourceNotFound;
import com.mb.common.repository.SemesterRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SemesterDaoImpl implements SemesterDao {

    private final SemesterRepository resultRepository;

    @Override
    public Semester findById(Long id) {
        return resultRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Semester doesn't exist!"));
    }

    @Override
    public boolean existsByStudentIdAndSemesterNumber(Long studentId, Integer semesterNumber) {
        return resultRepository.existsByStudentIdAndSemesterNumber(studentId, semesterNumber);
    }

    @Override
    public Semester save(Semester result) {
        return resultRepository.save(result);
    }

    @Override
    public List<Semester> findByStudentId(Long studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    @Override
    public List<Semester> findBySemesterOrderByPercentageDesc(Integer semester) {
        return resultRepository
                .findBySemesterNumberOrderByPercentageDesc(semester);
    }

    @Override
    public List<Semester> findLowPerformance(Double minPercentage) {
        return resultRepository.findByPercentageLessThan(minPercentage);
    }
}