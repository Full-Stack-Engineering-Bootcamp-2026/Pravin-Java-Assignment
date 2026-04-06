package com.cdac.service;

import com.cdac.dto.RoleInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Role;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.RoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  public Role saveRole(RoleInDto dto) {
    Role role = new Role();
    role.setDescription(dto.getDescription());
    role.setTitle(dto.getTitle());
    Role res = roleRepository.save(role);
    return res;
  }

  public List<Employee> getEmployeeOfRole(Long roleId) {
    Role role = roleRepository
      .findById(roleId)
      .orElseThrow(() -> new ResourceNotFound("Role not found!"));
    return role.getEmployees();
  }
}
