package com.byq.demo.security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jack on 2017/4/27.
 */
//contentType:img.json,xml,pdf
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired UserDetailsService service;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	 http
    	 .csrf().disable()
         .httpBasic().disable()
         
         .headers()
         .frameOptions()
         .disable()
         .and()
         .requestMatchers()
         .and()
         .authorizeRequests()
         
 //        .antMatchers("/swagger-resources/**","/actuator/**", "/api-docs/**", "/h2-console/**", "/signin", "/authorize", "/signup").permitAll()
   //      .antMatchers(HttpMethod.POST, "/oauth/token").permitAll()
     //    .antMatchers(HttpMethod.GET, "/product**/**").permitAll()
       //  .antMatchers(HttpMethod.GET, "/review/**").permitAll()
         //.antMatchers(HttpMethod.GET, "/image/**").permitAll()
 //        .antMatchers("/product/list").permitAll()
         .antMatchers("/product/add").hasRole("ADMIN")
         .antMatchers("/product/hasquantity").permitAll()
         .antMatchers("/product/hasquantitybetween").permitAll()
         .antMatchers("/h2-console/**").permitAll()
         
        
         .antMatchers("**").permitAll();
    //	  http.formLogin().loginProcessingUrl("/user/login");
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //memory(auth);
    	real(auth);
        //simpleUsingUerDetailsService(auth);
    }

	private void memory(AuthenticationManagerBuilder auth) throws Exception {
		auth
            .inMemoryAuthentication()
            .passwordEncoder(new BCryptPasswordEncoder())
            .withUser("root")
            .password(new BCryptPasswordEncoder().encode("root"))
            .roles("USER");
	}

	private void simpleUsingUerDetailsService(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();
				GrantedAuthority ag = new SimpleGrantedAuthority("USER");
				permissions.add(ag);
				User user = new User("root", new BCryptPasswordEncoder().encode("root"), permissions);
				
				
				return user;
			};
        }).passwordEncoder(new BCryptPasswordEncoder());
	}

	private void real(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.service).passwordEncoder(new BCryptPasswordEncoder());
	}

}