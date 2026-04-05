package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.DepartmentInDto;
import com.cdac.service.DepartmentServiceImpl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentServiceImpl departmentService;

	@PostMapping()
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentInDto dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.saveDepartment(dto));
	}

	@GetMapping()
	public ResponseEntity<?> findDepartments() {

		return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.findAllDepartment());
	}

	@GetMapping("/{deptId}/employees")
	public ResponseEntity<?> getEmployeesFromDepartment(@PathVariable @NotNull Long deptId) {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.findEmployeesByDeptId(deptId));
	}

}
