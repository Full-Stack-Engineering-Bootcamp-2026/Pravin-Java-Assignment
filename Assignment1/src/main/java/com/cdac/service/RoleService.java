package com.cdac.service;

import java.util.List;

import com.cdac.dto.RoleInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Role;

public interface RoleService {

	public Role saveRole(RoleInDto dto);

	public List<Employee> getEmployeeOfRole(Long roleId);

}
