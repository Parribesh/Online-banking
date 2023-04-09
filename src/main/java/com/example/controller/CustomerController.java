package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Customer;
import com.example.domain.User;
import com.example.service.CustomerService;
import com.example.service.UserService;

@Controller
public class CustomerController {

	@Autowired CustomerService customerService;
	@Autowired UserService userService;
	
	@PostMapping("/api/customer/createCustomer")
	public ModelAndView createCustomer(@Validated @ModelAttribute Customer customer, BindingResult br ) {
		ModelAndView mv = new ModelAndView("customerForm");
		
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = userService.getUserByUsername(principal);
		customer.setUser(u);
		
		
		
		if(!br.hasErrors()) {
			customerService.createCustomer(customer);
		}
		
		authorities.forEach(e -> {
			
			if(e.getAuthority().toUpperCase().equals("USER")) {
				var temp = customerService.getCustomerByUserId(u.getUserId());
				mv.addObject("customers",temp);
			}else {
				mv.addObject("customers", customerService.getAllCustomers());
			};
		});
		
		
		return mv;
	}

	
	@GetMapping("/api/customer/deleteCustomer/{customerId}")
	public ModelAndView deleteCustomer(Customer customer, @PathVariable Long customerId ) {
		ModelAndView mv = new ModelAndView("customerForm");
		Customer c = customerService.getCustomerById(customerId);
		customerService.deleteCustomer(c);
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = userService.getUserByUsername(principal);
		mv.addObject("userId", u.getUserId());
		
		authorities.forEach(e -> {
			
			if(e.getAuthority().toUpperCase().equals("USER")) {
				var temp = customerService.getCustomerByUserId(u.getUserId());
				mv.addObject("customers",temp);
			}else {
				mv.addObject("customers", customerService.getAllCustomers());
			};
		});
		mv.addObject("c", c);
		return mv;
	}
	
	@GetMapping(value = {"/api/customer/updateCustomer/{customerId}"})
	public ModelAndView  updateCustomer(Customer customer, @PathVariable Long customerId ) {
		ModelAndView mv = new ModelAndView("customerForm");
		Customer c = customerService.getCustomerById(customerId);
		
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = userService.getUserByUsername(principal);
		mv.addObject("userId", u.getUserId());
		
		authorities.forEach(e -> {
			
			if(e.getAuthority().toUpperCase().equals("USER")) {
				var temp = customerService.getCustomerByUserId(u.getUserId());
				mv.addObject("customers",temp);
			}else {
				mv.addObject("customers", customerService.getAllCustomers());
			};
		});
		mv.addObject("c", c);
		return mv;
	}
	
	@GetMapping(value = {"/api/customer/customerForm"})
	public ModelAndView  getCustomerForm( Customer customer ) {
		ModelAndView mv = new ModelAndView("customerForm");
		
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = userService.getUserByUsername(principal);
		mv.addObject("userId", u.getUserId());
		List<Customer> customers = new ArrayList<>();
		authorities.forEach(e -> {
			if(e.getAuthority().equals("USER")) {
				var temp = customerService.getCustomerByUserId(u.getUserId());
				customers.add(temp);
				System.out.println("Temp: "+ customers);
				mv.addObject("customers",customers);
			}else {
				mv.addObject("customers", customerService.getAllCustomers());
			};
		});
		
		return mv;
	}
	
}
