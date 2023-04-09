package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Role;

@Service
public interface RoleService {
	public Role createRole(Role r);
	public void deleteRole(Role role);
	public Role getRoleWithId(Long id);
	public List<Role> findAll();
}
