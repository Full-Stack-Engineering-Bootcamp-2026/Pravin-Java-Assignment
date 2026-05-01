package com.mb.common.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mb.common.entities.Student;
import com.mb.common.exception.ResourceNotFound;
import com.mb.common.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Repository
public class StudentDaoImpl implements StudentDao {

    private final StudentRepository studentRepository;

    @Override
    public Student findByAuth0Id(String auth0Id) {

        Student student = studentRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new ResourceNotFound("auth0id doens't exist."));
        return student;

    }

    @Override
    public Student findByEnrollmentNo(String enrollmentNo) {

        return studentRepository.findByEnrollmentNumber(enrollmentNo)
                .orElseThrow(() -> new ResourceNotFound("enrollment number doens't exist."));
    }

    @Override
    public boolean isAlreadyRegistered(String auth0Id) {

        return studentRepository.existsByAuth0Id(auth0Id);
    }

    @Override
    public boolean isEmailTaken(String email) {

        return studentRepository.existsByEmail(email);
    }

    @Override
    public boolean isEnrollmentNumberTaken(String enrollmentNumber) {

        return studentRepository.existsByEnrollmentNumber(enrollmentNumber);
    }

    @Override
    public Student save(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {

        return studentRepository.findAllByIsActiveTrue();

    }

    @Override
    public Student findById(Long id) {

        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("student id doens't exist."));
    }

}