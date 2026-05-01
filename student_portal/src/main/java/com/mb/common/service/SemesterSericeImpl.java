
package com.mb.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mb.common.dao.ResultResponse;
import com.mb.common.dao.SemesterDao;
import com.mb.common.dao.StudentDao;
import com.mb.common.dto.LowPerformanceResponse;
import com.mb.common.dto.MeritResponse;
import com.mb.common.dto.ResultInDto;
import com.mb.common.dto.SemesterReportResponse;
import com.mb.common.entities.Semester;
import com.mb.common.entities.Student;
import com.mb.common.exception.DuplicateResourceException;
import com.mb.common.exception.InvalidRequestException;
import com.mb.common.exception.ResourceNotFound;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SemesterSericeImpl implements SemesterService {

    private final SemesterDao resultDao;
    private final StudentDao studentDao;
    private final ModelMapper mapper;

    @Override
    public ResultResponse addResult(Long studentId, ResultInDto dto) {

        Student student = studentDao.findById(studentId);

        List<Semester> existing = resultDao.findByStudentId(studentId);
        if (existing.size() >= 8) {
            throw new InvalidRequestException("Student already has results for all 8 semesters");
        }

        if (resultDao.existsByStudentIdAndSemesterNumber(studentId, dto.getSemesterNumber())) {
            throw new DuplicateResourceException(
                    "Result for semester " + dto.getSemesterNumber() + " already exists");
        }

        Semester result = new Semester();
        result.setStudent(student);
        result.setSemesterNumber(dto.getSemesterNumber());
        result.setPercentage(dto.getPercentage());

        Semester saved = resultDao.save(result);
        Integer star = calculateStars(saved.getPercentage());

        ResultResponse response = mapper.map(saved, ResultResponse.class);
        response.setStars(star);

        return response;
    }

    public ResultResponse updateResult(Long resultId, ResultInDto dto) {

        Semester result = resultDao.findById(resultId);

        result.setPercentage(dto.getPercentage());

        Semester updated = resultDao.save(result);

        ResultResponse response = mapper.map(updated, ResultResponse.class);

        return response;
    }

    @Override
    public List<MeritResponse> getMeritList(Integer semester) {

        List<Semester> results = resultDao
                .findBySemesterOrderByPercentageDesc(semester);

        if (results.isEmpty()) {
            throw new ResourceNotFound(
                    "No results found for semester " + semester);
        }

        List<MeritResponse> meritList = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            Semester result = results.get(i);

            MeritResponse merit = new MeritResponse();
            merit.setRank(i + 1);
            merit.setStudentName(result.getStudent().getName());
            merit.setEnrollmentNumber(result.getStudent().getEnrollmentNumber());
            merit.setPercentage(result.getPercentage());
            merit.setStars(calculateStars(result.getPercentage()));

            meritList.add(merit);
        }

        return meritList;
    }

    @Override
    public List<SemesterReportResponse> getSemesterReport(Integer semester) {

        List<Semester> results = resultDao
                .findBySemesterOrderByPercentageDesc(semester);

        if (results.isEmpty()) {
            throw new ResourceNotFound(
                    "No results found for semester " + semester);
        }

        List<SemesterReportResponse> report = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            Semester result = results.get(i);

            SemesterReportResponse response = new SemesterReportResponse();
            response.setStudentName(result.getStudent().getName());
            response.setEnrollmentNumber(result.getStudent().getEnrollmentNumber());
            response.setSemesterNumber(result.getSemesterNumber());
            response.setPercentage(result.getPercentage());
            response.setStudentEmail(result.getStudent().getEmail());
            response.setRank(i + 1);
            response.setStars(calculateStars(result.getPercentage()));

            report.add(response);
        }

        return report;
    }

    private Integer calculateStars(Double percentage) {

        if (percentage >= 90)
            return 5;
        if (percentage >= 75)
            return 4;
        if (percentage >= 50)
            return 3;
        if (percentage >= 35)
            return 2;
        return 0;
    }

    @Override
    public List<ResultResponse> findResultOfStudent(String auth0Id) {

        if (!studentDao.isAlreadyRegistered(auth0Id)) {
            throw new ResourceNotFound("user doesn't exist");

        }
        Student student = studentDao.findByAuth0Id(auth0Id);
        List<Semester> semester = resultDao.findByStudentId(student.getId());

        if (semester.size() == 0) {

            throw new InvalidRequestException("No Result Found for this user.");
        }
        List<ResultResponse> responses = semester.stream().map((s) -> {
            return mapper.map(s, ResultResponse.class);
        }).toList();

        return responses;

    }

    @Override
    public List<LowPerformanceResponse> getLowPerformanceReport() {
     
          List<Semester> results = resultDao
            .findLowPerformance(50.0);

        if (results.isEmpty()) {
            throw new ResourceNotFound("No low performance records found");
        }

        return results.stream()
            .map(result -> {
                LowPerformanceResponse response = new LowPerformanceResponse();
                response.setStudentName(result.getStudent().getName());
                response.setEnrollmentNumber(result.getStudent().getEnrollmentNumber());
                response.setSemesterNumber(result.getSemesterNumber());
                response.setPercentage(result.getPercentage());
                response.setReason(
                    result.getPercentage().compareTo(50.0) < 0
                        ? "Failing"
                        : "Below 50%"
                );
                return response;
            })
            .collect(Collectors.toList());
    }
}