package com.cdac.service;

import com.cdac.dto.ProjectInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface ProjectService {
  Project saveProject(ProjectInDto dto);

  Project findProjectById(Long id);

  List<Project> findAllProjects();

  String deleteProject(Long id);

  List<Employee> findEmployeeByProjectId(@NotNull Long projectId);
}
