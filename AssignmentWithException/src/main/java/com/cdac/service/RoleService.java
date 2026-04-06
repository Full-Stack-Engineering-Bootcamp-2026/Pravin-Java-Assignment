package com.cdac.service;

import com.cdac.dto.RoleInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Role;
import java.util.List;

public interface RoleService {
  Role saveRole(RoleInDto dto);

  List<Employee> getEmployeeOfRole(Long roleId);
}
