package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Role;
import com.example.domain.User;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		List<GrantedAuthority> ga = new ArrayList<>();
		
		for(Role r: user.getUserRoles()) {
			ga.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserPassword(),ga );
	}

}
