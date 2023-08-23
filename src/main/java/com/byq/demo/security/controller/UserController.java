package com.byq.demo.security.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byq.demo.order.model.Cart;
import com.byq.demo.order.repo.CartRepository;
import com.byq.demo.product.model.Product;
import com.byq.demo.security.model.MyUser;
import com.byq.demo.security.repository.MyUserRepository;

//ORM

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired MyUserRepository repo;
	@Autowired CartRepository crepo;
	@Autowired
    AuthenticationManager authenticationManager;
	@RequestMapping(value = "signup", method=RequestMethod.POST)
	public MyUser signup(@RequestBody MyUser user) {
		user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
		repo.save(user);
		
		
		
		Cart cart = new Cart();
		cart.setUserId(user.getName());
		crepo.save(cart);
		return user;
	}
	@RequestMapping(value="login", method = RequestMethod.POST)
    public AccountInfo login(@RequestBody MyUser user) {

        //does the authentication
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getName(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> ps = authentication.getAuthorities();
        AccountInfo rst = new AccountInfo();
        rst.name = authentication.getName();
        for(GrantedAuthority da : ps) {
        	rst.permissions.add(da.getAuthority());
        }
        
        return rst;
    }
}
class AccountInfo{
	public String name;
	public List<String> permissions = new LinkedList<String>();
}
