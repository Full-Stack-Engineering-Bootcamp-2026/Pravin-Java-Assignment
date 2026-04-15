package com.cdac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	
	
	
	@EntityGraph(value = "Employee.withAll")
	 List<Employee> findAll();
	 @EntityGraph(value = "Employee.withAll")
	 Optional<Employee> findByEmail(String email);

     boolean existsByEmail(String email);
     
	

}
