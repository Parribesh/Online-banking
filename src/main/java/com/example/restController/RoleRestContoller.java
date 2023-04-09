package com.example.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Role;
import com.example.service.RoleService;
import com.example.validation.RoleValidator;

@RestController
public class RoleRestContoller {
	
	@Autowired RoleService roleService;
	@Autowired RoleValidator roleValidator;

	
	@PostMapping("rest/api/role/createRole")
	public Role createRole(@Validated @RequestBody Role r, BindingResult br) {

			Role aquiredRole = roleService.createRole(r);
		return aquiredRole;
	}

	@GetMapping("rest/api/role/getRole/{roleId}")
	public ResponseEntity<Role> getRole(@PathVariable Long roleId) {
		Role aquiredRole = roleService.getRoleWithId(roleId);
		return new ResponseEntity<Role>(aquiredRole, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("rest/api/role/deleteRole/{roleId}")
	public void deleteRole(Role role, @PathVariable Long roleId) {
		Role acquiredRole = roleService.getRoleWithId(roleId);
		roleService.deleteRole(acquiredRole);
	}
	
	@GetMapping("rest/api/role/updateRole/{roleId}")
	public Role updateRole(@PathVariable Long roleId) {
		Role acquiredRole = roleService.getRoleWithId(roleId);
		return acquiredRole;
	}
	
	@GetMapping("rest/api/role/findAll")
	public List<Role> findAll() {
		return roleService.findAll();
	}
	

}
