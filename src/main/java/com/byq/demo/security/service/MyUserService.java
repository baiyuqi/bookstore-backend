package com.byq.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.byq.demo.security.model.MyUser;
import com.byq.demo.security.repository.MyUserRepository;

@Service
public class MyUserService {
	@Autowired MyUserRepository rep;

	public void add(MyUser user) {
		String hashed = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(hashed);
		rep.save(user);
		
	}

}
