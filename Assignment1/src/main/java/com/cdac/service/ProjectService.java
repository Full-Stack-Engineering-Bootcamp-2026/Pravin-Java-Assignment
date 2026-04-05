package com.cdac.service;

import java.util.List;

import com.cdac.dto.ProjectInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;

import jakarta.validation.constraints.NotNull;

public interface ProjectService {

	public Project saveProject(ProjectInDto dto);

	public Project findProjectById(Long id);

	public List<Project> findAllProjects();

	public String deleteProject(Long id);

	public List<Employee> findEmployeeByProjectId(@NotNull Long projectId);

}
