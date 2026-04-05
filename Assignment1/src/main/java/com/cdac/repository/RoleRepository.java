package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
