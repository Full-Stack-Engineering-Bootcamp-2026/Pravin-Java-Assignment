package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
