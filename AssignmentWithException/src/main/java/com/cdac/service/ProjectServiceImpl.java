package com.cdac.service;

import com.cdac.dto.ProjectInDto;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.ProjectRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;

  public Project saveProject(ProjectInDto dto) {
    Project project = new Project();
    project.setDescription(dto.getDescription());
    project.setStartDate(dto.getStartDate());
    project.setEndDate(dto.getEndDate());
    project.setName(dto.getName());

    return projectRepository.save(project);
  }

  public Project findProjectById(Long id) {
    return projectRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFound(id + " Project is not exist"));
  }

  public List<Project> findAllProjects() {
    return projectRepository.findAll();
  }

  public String deleteProject(Long id) {
    Project res = projectRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFound(id + " Project is not exist"));
    projectRepository.delete(res);

    return "Project delted successfully!";
  }

  public List<Employee> findEmployeeByProjectId(@NotNull Long projectId) {
    Project res = projectRepository
      .findById(projectId)
      .orElseThrow(() -> new ResourceNotFound(projectId + " Project is not exist"));
    projectRepository.delete(res);

    return res.getEmployees();
  }
}
