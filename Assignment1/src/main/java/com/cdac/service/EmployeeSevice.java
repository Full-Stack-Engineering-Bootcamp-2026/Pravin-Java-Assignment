package com.cdac.service;

import java.util.List;

import com.cdac.dto.EmployeeInDto;
import com.cdac.dto.EmployeeOutDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;

public interface EmployeeSevice {

	public List<EmployeeOutDto> findAllEmployees();

	public Employee findEmployeeById(Long id);

	public EmployeeOutDto saveEmployee(EmployeeInDto emp);

	public List<Project> findProjectsByEmployeeId(Long id);

	public String deleteEmployeeById(Long empId);

	public String saveProjectInEmployee(Long userId, Long projecId);

	public String countEmployees();

	public Employee updateEmployee(EmployeeInDto emp, Long empId);

}
