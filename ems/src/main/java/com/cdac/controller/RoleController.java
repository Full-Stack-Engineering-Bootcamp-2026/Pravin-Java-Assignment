package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.RoleInDto;
import com.cdac.service.RoleService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {

		this.roleService = roleService;

		// TODO Auto-generated constructor stub
	}

	@PostMapping
	public ResponseEntity<?> createRole(@RequestBody @Valid RoleInDto dto) {

		System.out.println(dto.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveRole(dto));
	}

	@GetMapping("/{roleId}/employees")
	public ResponseEntity<?> getEmployessOfRole(@PathVariable @NotNull Long roleId) {

		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.getEmployeeOfRole(roleId));
	}

}
