package com.byq.demo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.security.model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, String>{

}
