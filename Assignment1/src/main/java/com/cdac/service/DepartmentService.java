package com.cdac.service;

import java.util.List;

import com.cdac.dto.DepartmentInDto;
import com.cdac.entities.Department;
import com.cdac.entities.Employee;

public interface DepartmentService {

	public Department saveDepartment(DepartmentInDto dto);

	public List<Department> findAllDepartment();

	public List<Employee> findEmployeesByDeptId(Long deptId);

}
