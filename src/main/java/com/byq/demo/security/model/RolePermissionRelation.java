package com.byq.demo.security.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class RolePermissionRelation {
	@Id
	String id;
	String permissionId;
	String roleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
