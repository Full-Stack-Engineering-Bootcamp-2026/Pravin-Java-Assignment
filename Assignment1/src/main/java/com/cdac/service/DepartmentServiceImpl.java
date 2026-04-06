package com.cdac.service;

import com.cdac.dto.DepartmentInDto;
import com.cdac.entities.Department;
import com.cdac.entities.Employee;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.DepartmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepository departmentRepository;

  public Department saveDepartment(DepartmentInDto dto) {
    Department dept = new Department();

    dept.setLocation(dto.getLocation());
    dept.setName(dto.getName());

    Department res = departmentRepository.save(dept);

    return res;
  }

  public List<Department> findAllDepartment() {
    return departmentRepository.findAll();
  }

  public List<Employee> findEmployeesByDeptId(Long deptId) {
    Department dept = departmentRepository
      .findById(deptId)
      .orElseThrow(() -> new ResourceNotFound("Department not found"));

    return dept.getEmployees();
  }
}
