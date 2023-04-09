package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role deleteRoleByroleId(Long id);
	public Role findRoleByroleId(Long id);

}
