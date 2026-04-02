package com.cdac.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.EmployeeInDto;
import com.cdac.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@GetMapping()
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.ok(empService.findAllEmployees());
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeInDto emp) {
		// TODO: process POST request

		System.out.println(emp.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(empService.saveEmployee(emp));
	}

	@PutMapping("/{empId}")
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeInDto emp, @PathVariable @NotNull Long empId) {
		// TODO: process POST request

		System.out.println(emp.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(empService.updateEmployee(emp, empId));
	}

	// what should the url?
	@PutMapping("/projects/{employeeId}/{projectId}")
	public ResponseEntity<?> assignProjectsToEmployee(@PathVariable @NotNull Long employeeId,
			@PathVariable @NotNull Long projectId) {

		return ResponseEntity.status(HttpStatus.OK).body(empService.saveProjectInEmployee(employeeId, projectId));
	}

	@GetMapping("/projects/{userId}")
	public ResponseEntity<?> getProjects(@PathVariable @NotNull Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(empService.findProjectsByEmployeeId(userId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findEmployeeById(@PathVariable @Positive Long id) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.OK).body(empService.findEmployeeById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable @Positive Long id) {
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
