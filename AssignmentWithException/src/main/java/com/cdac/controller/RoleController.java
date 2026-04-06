package com.cdac.controller;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.RoleInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Role;
import com.cdac.service.RoleServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
  private final RoleServiceImpl roleService;

  @PostMapping
  public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody @Valid RoleInDto dto) {
    System.out.println(dto.toString());

    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(new ApiResponse<Role>(true, null, roleService.saveRole(dto)));
  }

  @GetMapping("/{roleId}/employees")
  public ResponseEntity<ApiResponse<List<Employee>>> getEmployessOfRole(
    @PathVariable @NotNull Long roleId
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        new ApiResponse<List<Employee>>(true, null, roleService.getEmployeeOfRole(roleId))
      );
  }
}
