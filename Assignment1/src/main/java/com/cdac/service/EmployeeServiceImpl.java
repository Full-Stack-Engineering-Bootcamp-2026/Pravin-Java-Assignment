package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.EmployeeInDto;
import com.cdac.dto.EmployeeOutDto;
import com.cdac.entities.Department;
import com.cdac.entities.Employee;
import com.cdac.entities.Project;
import com.cdac.entities.Role;
import com.cdac.exception.ResourceNotFound;
import com.cdac.repository.DepartmentRepository;
import com.cdac.repository.EmployeeRepository;
import com.cdac.repository.ProjectRepository;
import com.cdac.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeSevice {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final ModelMapper mapper;
	private final ProjectRepository projectRepository;
	private final RoleRepository roleRepository;

	public List<EmployeeOutDto> findAllEmployees() {

		List<Employee> emps = employeeRepository.findAll();

		return emps.stream().map((e) -> {

			return mapper.map(e, EmployeeOutDto.class);

		}).toList();
	}

	public Employee findEmployeeById(Long id) {

		Employee emp = null;

		emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User doesn't exist!"));

		return emp;
	}

	public EmployeeOutDto saveEmployee(EmployeeInDto emp) {

		Optional<Department> dept = departmentRepository.findById(emp.getDepartment_id());
		Optional<Role> role = roleRepository.findById(emp.getRole());
		if (dept.isEmpty() || role.isEmpty()) {

			throw new ResourceNotFound("dept or role  not exist");

		}

		Employee empNew = new Employee();
		empNew.setName(emp.getName());
		empNew.setDepartment(dept.get());
		empNew.setEmail(emp.getEmail());
		empNew.setStatus(emp.getStatus());
		empNew.setRole(role.get());
		empNew.setSalary(emp.getSalary());

		Employee res = employeeRepository.save(empNew);

		return mapper.map(res, EmployeeOutDto.class);

	}

	public String saveProjectInEmployee(Long userId, Long projecId) {

		Employee emp = employeeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));
		Project project = projectRepository.findById(projecId)
				.orElseThrow(() -> new ResourceNotFound("project doesn't exist"));

		emp.getProjects().add(project);
		employeeRepository.save(emp);

		return "Project Saved Successfully!";

	}

	public List<Project> findProjectsByEmployeeId(Long id) {

		Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));

		return emp.getProjects();
	}

	public String deleteEmployeeById(Long empId) {

		Employee empNew = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));

		employeeRepository.delete(empNew);
		return "User Deleted Successfully!";

	}

	public String countEmployees() {

		return "Total count is : " + employeeRepository.count();
	}

	public Employee updateEmployee(EmployeeInDto emp, Long empId) {

		Employee empNew = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));
		Optional<Department> dept = departmentRepository.findById(emp.getDepartment_id());
		Optional<Role> role = roleRepository.findById(emp.getRole());
		if (dept.isEmpty() || role.isEmpty()) {

			throw new ResourceNotFound("dept or role  not exist");

		}
		// how to update employee ?
		empNew.setName(emp.getName());
		empNew.setDepartment(dept.get());
		empNew.setEmail(emp.getEmail());
		empNew.setStatus(emp.getStatus());
		empNew.setRole(role.get());
		empNew.setSalary(emp.getSalary());

		return employeeRepository.save(empNew);
	}

}
