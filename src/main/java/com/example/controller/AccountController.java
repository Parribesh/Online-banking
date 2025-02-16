package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Account;
import com.example.domain.Customer;
import com.example.service.AccountService;
import com.example.service.CustomerService;
import com.example.service.UserService;

@Controller
public class AccountController {
	
	@Autowired AccountService accountService;
	@Autowired UserService userService;
	@Autowired CustomerService customerService;
	
	@PostMapping("api/account/createAccount")
	public ModelAndView createAccount(@Validated @ModelAttribute Account account, BindingResult br ) {
		ModelAndView mv = new ModelAndView("accountForm");
		
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		long userId = userService.getUserByUsername(principal).getUserId();
		Customer c = null;
		if( (c = customerService.getCustomerByUserId(userId)) != null) {
			c.getCustomerAccounts().add(account);
			account.setAccountCustomer(c);
			mv.addObject("isCustomer", true);
		}else {
			mv.addObject("isCustomer", false);
		}
		
		if(!br.hasErrors()){
			accountService.createAccount(account);
		}

		authorities.forEach(e -> {
			
			if(e.getAuthority().toUpperCase().equals("USER")) {
				var temp = accountService.getAllAccounts().stream().filter(ac -> ac.getAccountCustomer() != null ? 
																			ac.getAccountCustomer().getUser()
																			.getUsername().equals(principal): false).toList();
				mv.addObject("accounts", temp);
				mv.addObject("hasAuthority", false);
			}else {
				mv.addObject("accounts", accountService.getAllAccounts() );
				mv.addObject("hasAuthority", true);
			};
		});
		return mv;
	}
	
	@GetMapping("api/account/updateAccount/{accountId}")
	public ModelAndView updateAccount(Account account, @PathVariable Long accountId){
		ModelAndView mv = new ModelAndView("accountForm");
		Account a = accountService.getAccountById(accountId);
		mv.addObject("a", a);
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		authorities.forEach(e -> {
			
			if(e.getAuthority().toUpperCase().equals("USER")) {
				var temp = accountService.getAllAccounts().stream().filter(ac -> ac.getAccountCustomer() != null ? 
																			ac.getAccountCustomer().getUser()
																			.getUsername().equals(principal): false).toList();
				mv.addObject("accounts", temp);
				mv.addObject("hasAuthority", false);
			}else {
				mv.addObject("accounts", accountService.getAllAccounts() );
				mv.addObject("hasAuthority", true);
			};
		});
		
		long userId = userService.getUserByUsername(principal).getUserId();
		if(customerService.getCustomerByUserId(userId) != null) {
			mv.addObject("isCustomer", true);
		}else {
			mv.addObject("isCustomer", false);
		}
		
		return mv;
	}
	
	@GetMapping("api/account/deleteAccount/{accountId}")
	public ModelAndView deleteAccount(Account account, @PathVariable Long accountId){
		ModelAndView mv = new ModelAndView("accountForm");
		Account a = accountService.getAccountById(accountId);
		accountService.deleteAccount(a);
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		authorities.forEach(e -> {
			
			if(e.getAuthority().toUpperCase().equals("USER")) {
				var temp = accountService.getAllAccounts().stream().filter(ac -> ac.getAccountCustomer() != null ? 
																			ac.getAccountCustomer().getUser()
																		.getUsername().equals(principal): false).toList();
				mv.addObject("accounts", temp);
				mv.addObject("hasAuthority", false);
			}else {
				mv.addObject("accounts", accountService.getAllAccounts() );
				mv.addObject("hasAuthority", true);
			};
		});
		return mv;
	}
	
	@GetMapping("api/account/accountForm")
	public ModelAndView getAccountForm(Account account) {
		System.out.println("Request to the faccountFrom Received...");
		ModelAndView mv = new ModelAndView("accountForm");
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		authorities.forEach(e -> {
			
			if(e.getAuthority().toUpperCase().equals("USER")) {
				var temp = accountService.getAllAccounts().stream().filter(ac -> ac.getAccountCustomer() != null ? 
																			ac.getAccountCustomer().getUser()
																		.getUsername().equals(principal): false).toList();
				mv.addObject("accounts", temp);
				mv.addObject("hasAuthority", false);
			}else {
				mv.addObject("accounts", accountService.getAllAccounts() );
				mv.addObject("hasAuthority", true);
			};
		});
		
		long userId = userService.getUserByUsername(principal).getUserId();
		if(customerService.getCustomerByUserId(userId) != null) {
			mv.addObject("isCustomer", true);
		}else {
			mv.addObject("isCustomer", false);
		}
		
		
		
		return mv;
	}
	
}
