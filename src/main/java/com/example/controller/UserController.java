package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import com.example.validation.UserValidator;

@Controller
public class UserController {
	
	@Autowired UserService userService;
	@Autowired UserValidator userValidator;
	@Autowired RoleService roleService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	@PostMapping("/api/user/createUser")
	public ModelAndView createUser(@Validated @ModelAttribute User user, BindingResult br ) {
		
		ModelAndView mv = new ModelAndView("userForm");
		System.out.println("user"+ user.getUserId());
//		User updatedWithPassword = User.withDefaultEncoder(user.getUserId(), user.getUsername(), user.getUserPassword(), user.getUserEmail(),  user.getUserMobile(), user.getUserRoles());
		if(!br.hasErrors()) {
			User u = userService.createUser(user);
		}
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		authorities.forEach(e -> {
			System.out.println("e.getAuthority(): "+e.getAuthority());
			if(e.getAuthority().toLowerCase().equals("user")){
				mv.addObject("users", userService.findAll().stream().filter(u -> u.getUsername().equals(principal)).toList());
			}else {
				mv.addObject("users", userService.findAll());
			};
		});
			
	
				mv.addObject("roles", roleService.findAll());
			

		
		return mv;
	}
	
	@RequestMapping("/api/user/getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId){
		User u = userService.getUser(userId);
		return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = {"/api/user/updateUser/{userId}"})
	public ModelAndView updateUser(User user, @PathVariable Long userId){
		User retrievedUser = userService.getUser(userId);
		ModelAndView mv = new ModelAndView("userForm");
		mv.addObject("u", retrievedUser);
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		authorities.forEach(e -> {
			System.out.println("e.getAuthority(): "+e.getAuthority());
			if(e.getAuthority().toLowerCase().equals("user")) {
				mv.addObject("users", userService.findAll().stream().filter(usr -> usr.getUsername().equals(principal)).toList());
			}else {
				mv.addObject("users", userService.findAll());
			};
		});
		
		mv.addObject("roles", roleService.findAll());
		mv.addObject("roles", roleService.findAll());
		
		return mv;
	}
	
	@RequestMapping(value = {"/api/user/deleteUser/{userId}"}, method = RequestMethod.GET)
	public ModelAndView deleteUser(User user, @PathVariable Long userId){
		User u = userService.deleteUserByUserId(userId);
		ModelAndView mv = new ModelAndView("userForm");
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		authorities.forEach(e -> {
			System.out.println("e.getAuthority(): "+e.getAuthority());
			if(e.getAuthority().toLowerCase().equals("user")) {
				mv.addObject("users", userService.findAll().stream().filter(usr -> usr.getUsername().equals(principal)).toList());
			}else {
				mv.addObject("users", userService.findAll());
			};
		});
		
		mv.addObject("roles", roleService.findAll());
		
		return mv;
	}
	
	@RequestMapping("/api/user/userForm")
	public ModelAndView userForm(@ModelAttribute User u){
		ModelAndView mv = new ModelAndView("userForm");
		mv.addObject("roles", roleService.findAll());
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		authorities.forEach(e -> {
			
			if(e.getAuthority().toLowerCase().equals("user")) {
				var temp = userService.findAll().stream().filter(us -> 
					us.getUsername().equals(principal)).toList();
				System.out.println(temp.size());
				mv.addObject("users", temp);
			}else {
				mv.addObject("users", userService.findAll());
			};
		});
			
		return mv;
	}


}
