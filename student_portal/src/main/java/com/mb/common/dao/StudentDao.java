package com.mb.common.dao;

import java.util.List;
import java.util.Optional;

import com.mb.common.dto.StudentInDto;
import com.mb.common.entities.Student;

public interface StudentDao {

    Student findByEnrollmentNo(String enrollmentNo);

    Student save(Student student);

    Student findByAuth0Id(String auth0Id);

    boolean isEmailTaken(String email);

    boolean isEnrollmentNumberTaken(String enrollmentNumber);

    boolean isAlreadyRegistered(String auth0Id);

    List<Student> findAllStudents();

    Student findById(Long id);

}