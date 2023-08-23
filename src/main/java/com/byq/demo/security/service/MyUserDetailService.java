package com.byq.demo.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.byq.demo.security.model.MyUser;
import com.byq.demo.security.model.RolePermissionRelation;
import com.byq.demo.security.model.UserRoleRelation;
import com.byq.demo.security.repository.MyUserRepository;
import com.byq.demo.security.repository.RolePermissionRelationRepository;
import com.byq.demo.security.repository.RoleRepository;
import com.byq.demo.security.repository.UserRoleRelationRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	MyUserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserRoleRelationRepository relationRepo;
	@Autowired
	RolePermissionRelationRepository rprelationRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// .....
		// spring security 引擎中存在用户、权限这套访问控制元素
		Optional<MyUser> uo = userRepo.findById(username);
		MyUser mu = uo.orElseThrow(() -> new RuntimeException("UserName doesn't exist!"));
		List<GrantedAuthority> permissions = collectPermissions(username);
		User user = new User(username, mu.getPassword(), permissions);
		return user;
	}

	private List<GrantedAuthority> collectPermissions(String username) {
		List<UserRoleRelation> roles = relationRepo.findByUserId(username);
		List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();
		for (UserRoleRelation r : roles) {
			GrantedAuthority ag = new SimpleGrantedAuthority(r.getRoleId());
			permissions.add(ag);
			List<RolePermissionRelation> ps = rprelationRepo.findByRoleId(r.getRoleId());
			for(RolePermissionRelation p : ps) {
				ag = new SimpleGrantedAuthority(p.getPermissionId());
				permissions.add(ag);
			}
			
		}
		permissions.add(new SimpleGrantedAuthority("ADMIN_USER"));
		permissions.add(new SimpleGrantedAuthority("USER"));
		return permissions;
	}

}