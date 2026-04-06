package com.cdac.service;

import com.cdac.dto.DepartmentInDto;
import com.cdac.entities.Department;
import com.cdac.entities.Employee;
import java.util.List;

public interface DepartmentService {
  public Department saveDepartment(DepartmentInDto dto);

  public List<Department> findAllDepartment();

  public List<Employee> findEmployeesByDeptId(Long deptId);
}
