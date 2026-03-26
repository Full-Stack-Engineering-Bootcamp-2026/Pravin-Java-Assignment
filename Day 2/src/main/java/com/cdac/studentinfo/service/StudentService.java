package com.cdac.studentinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.studentinfo.model.Student;

@Service
public class StudentService {
	private List<Student> list = List.of(new Student(1, "Pravin", "DAC-26", "email@gmail.com"),
			new Student(2, "Priya", "DAC-26", "email@gmail.com"), new Student(3, "Shyam", "DAC-26", "email@gmail.com"));

	public List<Student> getStudents() {

		return list;
	}

	public Student getStudentById(int id) {

		return list.stream().filter(t -> t.getId() == id).toList().getFirst();
	}

}
