package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Role;
import com.example.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired RoleRepository roleRepo;

	@Override
	public Role createRole(Role r) {
//		User u = r.getUsers();
		System.out.println("Users"+ r.getUsers());
		Role role = roleRepo.save(r);
		return role;
	}

	@Override
	public void deleteRole(Role role) {
		roleRepo.delete(role);
	}

	@Override
	public Role getRoleWithId(Long id) {
		Role r = roleRepo.findRoleByroleId(id);
		return r;
	}

	@Override
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

}
