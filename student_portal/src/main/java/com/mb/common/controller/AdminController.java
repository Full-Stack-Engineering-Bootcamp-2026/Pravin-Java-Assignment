package com.mb.common.controller;

import java.util.List;

import org.h2.result.ResultRemote;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mb.common.dao.ResultResponse;
import com.mb.common.dto.ApiResponse;
import com.mb.common.dto.LowPerformanceResponse;
import com.mb.common.dto.ResultInDto;
import com.mb.common.dto.SemesterReportResponse;
import com.mb.common.dto.StudentResponse;
import com.mb.common.dto.StudentUpdateDto;
import com.mb.common.entities.Semester;
import com.mb.common.service.SemesterService;
import com.mb.common.service.StudentService;
import com.mb.common.utils.ApiRoutes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.ADMIN)
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final StudentService studentService;
    private final SemesterService resultService;

    @GetMapping(ApiRoutes.Admin.STUDENTS)
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents(

            @AuthenticationPrincipal Jwt jwt) {

        System.out.println(jwt.getSubject());
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ApiResponse<>(true, "Students fetched", students));
    }

    @GetMapping(ApiRoutes.Admin.STUDENT_BY_ID)
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(
            @PathVariable Long id) {
        StudentResponse student = studentService.findStudentById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Student fetched", student));
    }

    @PutMapping(ApiRoutes.Admin.STUDENT_BY_ID)
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentUpdateDto dto) {
        StudentResponse updated = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Student updated", updated));
    }

    @DeleteMapping(ApiRoutes.Admin.STUDENT_BY_ID)
    public ResponseEntity<ApiResponse<Void>> deactivateStudent(
            @PathVariable Long id) {
        studentService.deactivateStudent(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Student deactivated", null));
    }

    @PostMapping(ApiRoutes.Admin.ADD_RESULT)
    public ResponseEntity<ApiResponse<ResultResponse>> addResult(
            @PathVariable Long studentId,
            @Valid @RequestBody ResultInDto dto) {
        ResultResponse result = resultService.addResult(studentId, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Result added", result));
    }

    @PutMapping(ApiRoutes.Admin.UPDATE_RESULT)
    public ResponseEntity<ApiResponse<ResultResponse>> updateResult(
            @PathVariable Long resultId,
            @Valid @RequestBody ResultInDto dto) {
        ResultResponse result = resultService.updateResult(resultId, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Result updated", result));
    }

    @GetMapping(ApiRoutes.Admin.REPORTS)
    public ResponseEntity<ApiResponse<List<SemesterReportResponse>>> getSemesterReport(
            @RequestParam Integer semester) {
        List<SemesterReportResponse> report = resultService.getSemesterReport(semester);
        return ResponseEntity.ok(new ApiResponse<>(true, "Report fetched", report));
    }

    @GetMapping(ApiRoutes.Admin.LOWER_PERFORMANCE)
    public ResponseEntity<ApiResponse<List<LowPerformanceResponse>>> getLowPerformanceReport() {
        List<LowPerformanceResponse> report = resultService.getLowPerformanceReport();
        return ResponseEntity.ok(new ApiResponse<>(true, "Low performance report fetched", report));
    }
}