package com.cdac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.ProjectInDto;
import com.cdac.entities.Project;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.ProjectRepository;

import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class ProjectService {

	private ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {

		this.projectRepository = projectRepository;
		// TODO Auto-generated constructor stub
	}

	public ApiResponse saveProject(ProjectInDto dto) {

		Project project = new Project();
		project.setDescription(dto.getDescription());
		project.setStartDate(dto.getStartDate());
		project.setEndDate(dto.getEndDate());
		project.setName(dto.getName());

		Project res = projectRepository.save(project);

		return new ApiResponse(true, "Project Created", res);
	}

	public ApiResponse findProjectById(Long id) {

		Project res = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound(id + " Project is not exist"));

		return new ApiResponse(true, "Project found!", res);
	}

	public ApiResponse findAllProjects() {

		List<Project> res = projectRepository.findAll();

		return new ApiResponse(true, null, res);
	}

	public ApiResponse deleteProject(Long id) {

		Project res = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound(id + " Project is not exist"));
		projectRepository.delete(res);

		return new ApiResponse(true, "Project Created" + id);
	}

	public ApiResponse findEmployeeByProjectId(@NotNull Long projectId) {
		// TODO Auto-generated method stub
		Project res = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFound(projectId + " Project is not exist"));
		projectRepository.delete(res);

		return new ApiResponse(true, null, res.getEmployees());
	}

}
