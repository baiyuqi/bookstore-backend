package com.byq.demo.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.security.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String>{

}
