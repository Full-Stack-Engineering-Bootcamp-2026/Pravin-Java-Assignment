package com.cdac.studentinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.studentinfo.model.Student;
import com.cdac.studentinfo.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {

	@Value("${app.batch.name}")
	private String batch;

	private final StudentService studentService;

	@GetMapping("/students")
	public List<Student> getStudentList() {

		return studentService.getStudents();
	}

	@GetMapping("/student")
	public Student getStudent(@RequestParam int id) {

		System.out.println(id);
		return studentService.getStudentById(id);
	}

	@GetMapping("/batch")
	public String getBatch() {

		return batch;
	}

}
