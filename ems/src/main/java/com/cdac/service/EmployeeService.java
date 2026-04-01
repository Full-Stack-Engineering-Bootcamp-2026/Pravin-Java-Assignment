package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.ApiResponse;
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
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final ModelMapper mapper;
	private final ProjectRepository projectRepository;
	private final RoleRepository roleRepository;

//	GET /employees — Return all employees
//	GET /employees/{id} — Return employee or 404
//	POST /employees — Create and return new employee with 201
//	PUT /employees/{id} — Update and return updated employee or 404
//	DELETE /employees/{id} — Delete and return 204
//	GET /employees/search?department=Engineering — Filter by department using @RequestParam

	public ApiResponse findAllEmployees() {

		List<Employee> emps = employeeRepository.findAll();

		List<EmployeeOutDto> newDto = emps.stream().map((e) -> {

			return mapper.map(e, EmployeeOutDto.class);

		}).toList();

		return new ApiResponse(true, "Employee found!", newDto);
	}

	public ApiResponse findEmployeeById(Long id) {

		Employee emp = null;

		emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User doesn't exist!"));

		return new ApiResponse(true, "User found!", emp);
	}

	public ApiResponse saveEmployee(EmployeeInDto emp) {

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

		EmployeeOutDto dto = mapper.map(res, EmployeeOutDto.class);

		return new ApiResponse(true, "Employee Created!", dto);

	}

	// is this approch good?
	public ApiResponse saveProjectInEmployee(Long userId, Long projecId) {

		Employee emp = employeeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));
		Project project = projectRepository.findById(projecId)
				.orElseThrow(() -> new ResourceNotFound("project doesn't exist"));

		emp.getProjects().add(project);
		employeeRepository.save(emp);

		return new ApiResponse(true, "Project Saved!");

	}

	// how to update ? what should be the approch?
	public ApiResponse findProjectsByEmployeeId(Long id) {

		Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));

		return new ApiResponse(true, "Projects found!", emp.getProjects());
	}

	// how to update ? what should be the approch?
	public ApiResponse updateEmployee(Employee empData, int id) {

		Employee emp = null;

		return null;
	}

	public ApiResponse deleteEmployeeById(int id) {

		return new ApiResponse(true, "User Deleted!");

	}

	public ApiResponse findDepartment(String dept) {

		return new ApiResponse(true, null);

	}

	public ApiResponse countEmployees() {
		// TODO Auto-generated method stub
		return new ApiResponse(true, "Total count is : ", employeeRepository.count());
	}

	public ApiResponse findMinSal(double sal) {

		return new ApiResponse(true, null);
	}

}
