package com.byq.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byq.demo.security.model.Role;
import com.byq.demo.security.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired RoleRepository rep;
	public void add(Role role) {
		rep.save(role);
	}

}
