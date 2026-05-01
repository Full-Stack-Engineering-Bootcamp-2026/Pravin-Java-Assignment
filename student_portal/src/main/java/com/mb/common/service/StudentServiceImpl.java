package com.mb.common.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mb.common.dao.RoleDao;
import com.mb.common.dao.StudentDao;
import com.mb.common.dto.StudentInDto;
import com.mb.common.dto.StudentResponse;
import com.mb.common.dto.StudentUpdateDto;
import com.mb.common.entities.Role;
import com.mb.common.entities.Student;
import com.mb.common.exception.DuplicateResourceException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final ModelMapper mapper;
    private final RoleDao roleDao;

    @Override
    public Student register(String auth0Id, String email, StudentInDto request) {

        if (studentDao.isAlreadyRegistered(auth0Id)) {
            throw new DuplicateResourceException("Student already registered");
        }

        if (studentDao.isEmailTaken(email)) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (studentDao.isEnrollmentNumberTaken(request.getEnrollmentNumber())) {
            throw new DuplicateResourceException("Enrollment number already exists");
        }

        Role role = roleDao.findById(1L);
        Student student = new Student();
        student.setAuth0Id(auth0Id);
        student.setEmail(email);
        student.setName(request.getName());
        student.setEnrollmentNumber(request.getEnrollmentNumber());
        student.setRole(role);
        student.setIsActive(true);

        Student saved = studentDao.save(student);
        return saved;
    }

    @Override
    public String getEmailByEnrollmentNumber(String enrollmentNumber) {

        return studentDao.findByEnrollmentNo(enrollmentNumber).getEmail();
    }

    @Override
    public Student getByAuth0Id(String auth0Id) {

        return studentDao.findByAuth0Id(auth0Id);

    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentDao.findAllStudents();

        List<StudentResponse> responses = students.stream().map((s) -> {

            return mapper.map(s, StudentResponse.class);
        }).toList();
        return responses;

    }

    @Override
    public StudentResponse updateStudent(Long id, StudentUpdateDto dto) {

        Student student = studentDao.findById(id);

        student.setName(dto.getName());
        student.setEnrollmentNumber(dto.getEnrollmentNumber());

        Student updated = studentDao.save(student);

        StudentResponse response = mapper.map(updated, StudentResponse.class);
        return response;
    }

    @Override
    public StudentResponse findStudentById(Long id) {

        Student student = studentDao.findById(id);
        StudentResponse response = mapper.map(student, StudentResponse.class);

        return response;
    }

    @Override
    public String deactivateStudent(Long id) {
        Student student = studentDao.findById(id);

        student.setIsActive(false);
        studentDao.save(student);

        return "Student with Id " + id + "is deleted!";
    }
}