package com.cdac.service;

import com.cdac.dto.AuthResponse;
import com.cdac.dto.ChangePasswordDTo;
import com.cdac.dto.EmployeeInDto;
import com.cdac.dto.EmployeeOutDto;
import com.cdac.dto.LoginInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;
import java.util.List;

public interface EmployeeSevice {
  List<EmployeeOutDto> findAllEmployees();

  Employee findEmployeeById(Long id);

 


 public EmployeeOutDto saveEmployeeByAdmin(EmployeeInDto emp);
  List<Project> findProjectsByEmployeeId(Long id);

  String deleteEmployeeById(Long empId);

  String saveProjectInEmployee(Long userId, Long projecId);

  String countEmployees();

  Employee updateEmployee(EmployeeInDto emp, Long empId);

  AuthResponse changePassword(  ChangePasswordDTo dto);

  AuthResponse loginEmployee(  LoginInDto dto);
}
