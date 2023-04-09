package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Role;
import com.example.service.RoleService;
import com.example.validation.RoleValidator;

@Controller
public class RoleController {
	
	@Autowired RoleService roleService;
	@Autowired RoleValidator roleValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(roleValidator);
	}
	
	@PostMapping("/api/role/createRole")
	public ModelAndView createRole(@Validated @ModelAttribute Role r, BindingResult br) {
		if(!br.hasErrors()) {
			Role aquiredRole = roleService.createRole(r);
		}
		ModelAndView mv = new ModelAndView("roleForm");
		mv.addObject("roles", roleService.findAll());
		return mv;
	}
	
	@GetMapping("/api/role/getRole/{roleId}")
	public ResponseEntity<Role> getRole(@PathVariable Long roleId) {
		Role aquiredRole = roleService.getRoleWithId(roleId);
		return new ResponseEntity<Role>(aquiredRole, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping("/api/role/deleteRole/{roleId}")
	@ResponseBody
	public ModelAndView deleteRole(Role role, @PathVariable Long roleId) {
		Role acquiredRole = roleService.getRoleWithId(roleId);
		roleService.deleteRole(acquiredRole);
		ModelAndView mv = new ModelAndView("roleForm");
		mv.addObject("roles", roleService.findAll());
		return mv;
	}
	
	@GetMapping("/api/role/updateRole/{roleId}")
	public ModelAndView updateRole(Role role, @PathVariable Long roleId) {
		ModelAndView mv = new ModelAndView("roleForm");
		Role acquiredRole = roleService.getRoleWithId(roleId);
		mv.addObject("roles", roleService.findAll());
		mv.addObject("r", acquiredRole);
		return mv;
	}
	
	@GetMapping("/api/role/findAll")
	@ResponseBody
	public List<Role> findAll() {
		return roleService.findAll();
	}
	
	@GetMapping("/api/role/roleForm")
	public ModelAndView getRoleForm(@ModelAttribute Role r, BindingResult br) {
		ModelAndView mv = new ModelAndView("roleForm");
		mv.addObject("roles", roleService.findAll());
		return mv;
	}
}
