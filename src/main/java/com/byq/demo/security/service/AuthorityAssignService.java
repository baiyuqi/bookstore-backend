package com.byq.demo.security.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byq.demo.security.model.RolePermissionRelation;
import com.byq.demo.security.model.UserRoleRelation;
import com.byq.demo.security.repository.RolePermissionRelationRepository;
import com.byq.demo.security.repository.UserRoleRelationRepository;
@Service
public class AuthorityAssignService {
	@Autowired UserRoleRelationRepository rep;
	@Autowired RolePermissionRelationRepository rprep;
	public void assignRole(String userId, String roleId) {
		UserRoleRelation re = new UserRoleRelation();
		re.setRoleId(roleId);
		re.setUserId(userId);
		String id = UUID.randomUUID().toString();
		re.setId(id);
		rep.save(re);
	}
	public void assignPermission(String roleId, String permissionId) {
		RolePermissionRelation re = new RolePermissionRelation();
		re.setRoleId(roleId);
		re.setPermissionId(permissionId);
		String id = UUID.randomUUID().toString();
		re.setId(id);
		rprep.save(re);
	}
}
