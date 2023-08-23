package com.byq.demo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
