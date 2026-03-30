package com.cdac.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entities.Employee;
import com.cdac.service.EmployeeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {

	private EmployeeService empService;

	public EmployeeController(EmployeeService service) {

		empService = service;
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.ok(empService.findAllEmployees());
	}

	@PostMapping("/")
	public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee emp) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.CREATED).body(empService.saveEmployee(emp));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findEmployeeById(@PathVariable @Positive int id) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.OK).body(empService.findEmployeeById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable @Positive int id) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(empService.deleteEmployeeById(id));
	}

//what should be the approach for this
	@GetMapping("/search")
	public ResponseEntity<?> searchDeapartment(
			@RequestParam(required = false) @NotBlank @Length(min = 1, max = 10) String dept,
			@RequestParam(required = false) @Positive Double minSal) {
		// TODO: process POST request
		if (dept != null) {
			return ResponseEntity.status(HttpStatus.OK).body(empService.findDepartment(dept));
		} else if (minSal != null) {
			return ResponseEntity.status(HttpStatus.OK).body(empService.findMinSal(minSal));
		}
		return ResponseEntity.status(HttpStatus.OK).body("No Filter Provided");
	}

	@GetMapping("/count")
	public ResponseEntity<?> countEmployee() {

		return ResponseEntity.status(HttpStatus.OK).body(empService.countEmployees());
	}

}
