package com.byq.demo.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.security.model.RolePermissionRelation;

public interface RolePermissionRelationRepository extends JpaRepository<RolePermissionRelation, String>{
	List<RolePermissionRelation> findByRoleId(String userId);
}
