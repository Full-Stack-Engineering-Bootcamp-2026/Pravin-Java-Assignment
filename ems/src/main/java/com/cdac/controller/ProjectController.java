package com.cdac.controller;

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

import com.cdac.dto.ProjectInDto;
import com.cdac.repository.ProjectRepository;
import com.cdac.service.ProjectService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/projects")
@Validated
public class ProjectController {

	private final ProjectService projectService;
	private final ProjectRepository projectRepository;

	public ProjectController(ProjectRepository projectRepository, ProjectService projectService) {

		this.projectRepository = projectRepository;

		this.projectService = projectService;
		// TODO Auto-generated constructor stub
	}

	@PostMapping()
	public ResponseEntity<?> createProject(@RequestBody @Valid ProjectInDto entity) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.saveProject(entity));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable @NotNull Long id) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.OK).body(projectService.findProjectById(id));
	}

	@GetMapping()
	public ResponseEntity<?> findProjects() {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.OK).body(projectService.findAllProjects());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProjectById(@PathVariable @NotNull Long id) {
		// TODO: process POST request

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(projectService.deleteProject(id));
	}

	@GetMapping("/{projectId}/employees")
	public ResponseEntity<?> getEmployeesOfProject(@PathVariable @NotNull Long projectId) {

		return ResponseEntity.status(HttpStatus.OK).body(projectService.findEmployeeByProjectId(projectId));

	}

}
