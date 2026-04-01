package com.cdac.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.DepartmentInDto;
import com.cdac.entities.Department;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {

	private DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		// TODO Auto-generated constructor stub

		this.departmentRepository = departmentRepository;
	}

	public ApiResponse saveDepartment(DepartmentInDto dto) {

		Department dept = new Department();

		dept.setLocation(dto.getLocation());
		dept.setName(dto.getName());

		Department res = departmentRepository.save(dept);

		return new ApiResponse(true, "Department Created!", res);
	}

	public ApiResponse findAllDepartment() {

		return new ApiResponse(true, null, departmentRepository.findAll());

	}

	public ApiResponse findEmployeesByDeptId(Long deptId) {
		// TODO Auto-generated method stub

		Department dept = departmentRepository.findById(deptId)
				.orElseThrow(() -> new ResourceNotFound("Department not found"));

		return new ApiResponse(true, null, dept.getEmployees());
	}

}
