package com.cdac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.DepartmentInDto;
import com.cdac.entities.Department;
import com.cdac.entities.Employee;
import com.cdac.service.DepartmentServiceImpl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentServiceImpl departmentService;

	@PostMapping()
	public ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody DepartmentInDto dto) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<Department>(true, null, departmentService.saveDepartment(dto)));
	}

	@GetMapping()
	public ResponseEntity<ApiResponse<List<Department>>> findDepartments() {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<List<Department>>(true, null, departmentService.findAllDepartment()));
	}

	@GetMapping("/{deptId}/employees")
	public ResponseEntity<ApiResponse<List<Employee>>> getEmployeesFromDepartment(@PathVariable @NotNull Long deptId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<List<Employee>>(true, null, departmentService.findEmployeesByDeptId(deptId)));
	}

}
