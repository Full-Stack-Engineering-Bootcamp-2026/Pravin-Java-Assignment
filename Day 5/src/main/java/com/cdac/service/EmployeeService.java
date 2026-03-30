package com.cdac.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.ApiResponse;
import com.cdac.entities.Employee;
import com.cdac.exception.ResourceNotFound;

@Service
@Transactional
public class EmployeeService {

	private List<Employee> emps = new ArrayList<Employee>();

//	GET /employees — Return all employees
//	GET /employees/{id} — Return employee or 404
//	POST /employees — Create and return new employee with 201
//	PUT /employees/{id} — Update and return updated employee or 404
//	DELETE /employees/{id} — Delete and return 204
//	GET /employees/search?department=Engineering — Filter by department using @RequestParam

	public ApiResponse findAllEmployees() {

		return new ApiResponse(true, "Employee found!", emps);
	}

	public ApiResponse findEmployeeById(int id) {

		Employee emp = null;

		emp = emps.stream().filter((t) -> t.getId() == id).toList().getFirst();

		if (emp == null) {

			throw new ResourceNotFound("Employee not found!");
		}
		return new ApiResponse(true, "User found!", emp);
	}

	public ApiResponse saveEmployee(Employee emp) {

		emps.add(emp);
		return new ApiResponse(true, "Employee Created!", emp);

	}

	// how to update ? what should be the approch?
	public ApiResponse updateEmployee(Employee empData, int id) {

		Employee emp = null;

		emp = emps.stream().filter((t) -> t.getId() == id).toList().getFirst();

		emp.setEmail(empData.getEmail());
		emp.setName(empData.getName());
		emp.setDepartment(empData.getDepartment());
		//

		return null;
	}

	public ApiResponse deleteEmployeeById(int id) {

		Employee emp = emps.stream().filter(t -> t.getId() != id).toList().getFirst();
		if (emp == null) {
			throw new ResourceNotFound("User not found!");
		}

		List<Employee> newEmps = emps.stream().filter(t -> t.getId() != id).toList();
		emps = newEmps;

		return new ApiResponse(true, "User Deleted!");

	}

	public ApiResponse findDepartment(String dept) {

		List<Employee> result = emps.stream().filter(t -> t.getDepartment().contains(dept)).toList();

		return new ApiResponse(true, null, result);

	}

	public ApiResponse countEmployees() {
		// TODO Auto-generated method stub
		return new ApiResponse(true, "Total count is : " + emps.size());
	}

	public ApiResponse findMinSal(double sal) {

		List<Employee> result = emps.stream().filter(t -> t.getSalary() >= sal).toList();
		return new ApiResponse(true, null, result);
	}

}
