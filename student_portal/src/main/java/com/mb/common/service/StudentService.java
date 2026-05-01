package com.mb.common.service;

import java.util.List;

import com.mb.common.dto.StudentInDto;
import com.mb.common.dto.StudentResponse;
import com.mb.common.dto.StudentUpdateDto;
import com.mb.common.entities.Student;

public interface StudentService {

  Student register(String auth0Id, String email, StudentInDto request);

  String getEmailByEnrollmentNumber(String enrollmentNumber);

  Student getByAuth0Id(String auth0Id);

  List<StudentResponse> getAllStudents();

  StudentResponse updateStudent(Long id, StudentUpdateDto dto);

  StudentResponse findStudentById(Long id);

  String deactivateStudent(Long id);
}