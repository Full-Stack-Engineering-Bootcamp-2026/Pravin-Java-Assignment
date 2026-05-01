package com.mb.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.mb.common.entities.Role;
import com.mb.common.exception.ResourceNotFound;
import com.mb.common.repository.RoleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Repository
public class RoleDaoImpl implements RoleDao {

    private final RoleRepository role;

    @Override
    public Role findById(Long id) {

        return role.findById(id).orElseThrow(() -> new ResourceNotFound("Role doesnot found!"));
    }

}