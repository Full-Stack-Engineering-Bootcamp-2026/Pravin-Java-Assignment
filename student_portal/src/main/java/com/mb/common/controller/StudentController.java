
package com.mb.common.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mb.common.dao.ResultResponse;
import com.mb.common.dto.ApiResponse;
import com.mb.common.dto.MeritResponse;
import com.mb.common.dto.StudentInDto;
import com.mb.common.entities.Student;
import com.mb.common.service.SemesterService;
import com.mb.common.service.StudentService;
import com.mb.common.utils.ApiRoutes;
import com.mb.common.utils.ApiRoutes.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping(ApiRoutes.STUDENTS)
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;
    private final SemesterService semesterService;

    @PostMapping(ApiRoutes.Student.REGISTER)

    public ResponseEntity<ApiResponse<Student>> registerStudent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody StudentInDto dto) {

        System.out.println(dto.getEnrollmentNumber() + dto.getName());

        String auth0 = jwt.getSubject();
        String email = jwt.getClaim("email");
        // String email = jwt.getClaimAsString("email");
        System.out.println("here is the claim" + auth0);

        Student response = studentService.register(auth0, email, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Student>(true, "Student Registerd!", response));

    }

    @GetMapping(ApiRoutes.Student.LOOKUP)

    public ResponseEntity<ApiResponse<String>> lookupEmail(
            @RequestParam @NotBlank String enrollmentNumber) {
        String email = studentService.getEmailByEnrollmentNumber(enrollmentNumber);
        return ResponseEntity.ok(new ApiResponse<>(true, "Email found", email));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(ApiRoutes.Student.ME)
    public ResponseEntity<ApiResponse<Student>> getMyProfile(
            @AuthenticationPrincipal Jwt jwt) {

        String auth0Id = jwt.getSubject();

        Student student = studentService.getByAuth0Id(auth0Id);

        return ResponseEntity.ok(
                new ApiResponse<Student>(true, "Profile fetched", student));
    }

    @GetMapping(ApiRoutes.Student.RESULT)
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<List<ResultResponse>>> getResult(@AuthenticationPrincipal Jwt jwt) {

        String auth0 = jwt.getSubject();

        List<ResultResponse> responses = semesterService.findResultOfStudent(auth0);

        return ResponseEntity.ok(new ApiResponse<>(true, "Resut found!", responses));

    }

    @GetMapping( ApiRoutes.Student.MERIT  )
    public ResponseEntity<ApiResponse<List<MeritResponse>>> getMeritList(@RequestParam Integer semester) {

        List<MeritResponse> responses = semesterService.getMeritList(semester);

        return ResponseEntity.ok(new ApiResponse<>(true, "Merit List for Semester " + semester, responses));

    }

}