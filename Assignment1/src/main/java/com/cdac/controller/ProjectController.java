package com.cdac.controller;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.DepartmentInDto;
import com.cdac.dto.ProjectInDto;
import com.cdac.entities.Department;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;
import com.cdac.repository.ProjectRepository;
import com.cdac.service.DepartmentService;
import com.cdac.service.ProjectServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@Validated
@RequiredArgsConstructor
public class ProjectController {
  private final ProjectServiceImpl projectService;
  private final ProjectRepository projectRepository;
  private final DepartmentService departmentService;

  @PostMapping
  public ResponseEntity<ApiResponse<Department>> createDepartment(
    @RequestBody DepartmentInDto dto
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        new ApiResponse<Department>(true, null, departmentService.saveDepartment(dto))
      );
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Project>> createProject(
    @RequestBody @Valid ProjectInDto entity
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(new ApiResponse<Project>(true, null, projectService.saveProject(entity)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Project>> getProjectById(
    @PathVariable @NotNull Long id
  ) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(new ApiResponse<Project>(true, null, projectService.findProjectById(id)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Project>>> findProjects() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(new ApiResponse<List<Project>>(true, null, projectService.findAllProjects()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> deleteProjectById(
    @PathVariable @NotNull Long id
  ) {
    return ResponseEntity
      .status(HttpStatus.NO_CONTENT)
      .body(new ApiResponse<String>(true, null, projectService.deleteProject(id)));
  }

  @GetMapping("/{projectId}/employees")
  public ResponseEntity<ApiResponse<List<Employee>>> getEmployeesOfProject(
    @PathVariable @NotNull Long projectId
  ) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(
        new ApiResponse<List<Employee>>(
          true,
          null,
          projectService.findEmployeeByProjectId(projectId)
        )
      );
  }
}
