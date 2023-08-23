package com.byq.demo.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.security.model.UserRoleRelation;

public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation, String>{
	List<UserRoleRelation> findByUserId(String userId);
}
