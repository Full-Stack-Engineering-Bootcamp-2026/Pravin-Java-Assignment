package com.cdac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.RoleInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Role;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {

		this.roleRepository = roleRepository;
		// TODO Auto-generated constructor stub
	}

	public Role saveRole(RoleInDto dto) {

		Role role = new Role();
		role.setDescription(dto.getDescription());
		role.setTitle(dto.getTitle());
		Role res = roleRepository.save(role);
		return res;

	}

	public List<Employee> getEmployeeOfRole(Long roleId) {

		Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFound("Role not found!"));
		return role.getEmployees();
	}

}
