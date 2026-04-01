package com.cdac.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.RoleInDto;
import com.cdac.entities.Role;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {

		this.roleRepository = roleRepository;
		// TODO Auto-generated constructor stub
	}

	public ApiResponse saveRole(RoleInDto dto) {

		Role role = new Role();
		role.setDescription(dto.getDescription());
		role.setTitle(dto.getTitle());
		Role res = roleRepository.save(role);
		return new ApiResponse(true, "Role Created ", res);

	}

	public ApiResponse getEmployeeOfRole(Long roleId) {
		// TODO Auto-generated method stub

		Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFound("Role not found!"));
		return new ApiResponse(true, null, role.getEmployees());
	}

}
