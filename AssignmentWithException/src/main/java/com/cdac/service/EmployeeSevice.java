package com.cdac.service;

import com.cdac.dto.EmployeeInDto;
import com.cdac.dto.EmployeeOutDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;
import java.util.List;

public interface EmployeeSevice {
  List<EmployeeOutDto> findAllEmployees();

  Employee findEmployeeById(Long id);

  EmployeeOutDto saveEmployee(EmployeeInDto emp);

  List<Project> findProjectsByEmployeeId(Long id);

  String deleteEmployeeById(Long empId);

  String saveProjectInEmployee(Long userId, Long projecId);

  String countEmployees();

  Employee updateEmployee(EmployeeInDto emp, Long empId);
}
