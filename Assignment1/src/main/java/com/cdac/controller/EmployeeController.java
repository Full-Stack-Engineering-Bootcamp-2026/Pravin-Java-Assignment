package com.cdac.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.EmployeeInDto;
import com.cdac.service.EmployeeServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeServiceImpl empService;

	@GetMapping()
	public ResponseEntity<ApiResponse> getAllEmployees() {
		System.out.println("caleed");
		return ResponseEntity.ok(new ApiResponse(true, null, empService.findAllEmployees()));
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse> createEmployee(@Valid @RequestBody EmployeeInDto emp) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse(true, "Employee Created Succesfully!", empService.saveEmployee(emp)));
	}

	@PutMapping("/{empId}")
	public ResponseEntity<ApiResponse> updateEmployee(@Valid @RequestBody EmployeeInDto emp,
			@PathVariable @NotNull Long empId) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse(true, "Employee updated successfully!", empService.updateEmployee(emp, empId)));
	}

	@PutMapping("/projects/{employeeId}/{projectId}")
	public ResponseEntity<ApiResponse> assignProjectsToEmployee(@PathVariable @NotNull Long employeeId,
			@PathVariable @NotNull Long projectId) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(true, null, empService.saveProjectInEmployee(employeeId, projectId)));
	}

	@GetMapping("/projects/{userId}")
	public ResponseEntity<?> getProjects(@PathVariable @NotNull Long userId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(true, null, empService.findProjectsByEmployeeId(userId)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findEmployeeById(@PathVariable @Positive Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(empService.findEmployeeById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable @Positive Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(empService.deleteEmployeeById(id));
	}

	@GetMapping("/count")
	public ResponseEntity<?> countEmployee() {

		return ResponseEntity.status(HttpStatus.OK).body(empService.countEmployees());
	}

}
