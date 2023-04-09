package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Account;
import com.example.domain.Customer;
import com.example.domain.Transaction;
import com.example.domain.User;
import com.example.model.DateModel;
import com.example.service.AccountService;
import com.example.service.CustomerService;
import com.example.service.TransactionService;
import com.example.service.UserService;

@Controller
public class TransactionController {
	
	@Autowired TransactionService tranService;
	@Autowired AccountService accService;
	@Autowired UserService userService;
	@Autowired CustomerService customerService;
	
	
	@GetMapping("/api/transaction/transactionForm")
	public ModelAndView getTransactionForm(Transaction transaction, @ModelAttribute DateModel dateModel) {
		ModelAndView mv = new ModelAndView("transactionForm");	
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = userService.getUserByUsername(principal);
		
		authorities.forEach(e -> {
			System.out.println(e.getAuthority());
			if(e.getAuthority().equals("USER")) {
				Customer c = customerService.getCustomerByUserId(u.getUserId());
				List<Account> accounts = accService.getAccountsByCustomerId(c.getCustomerId());
				
				List<Transaction> trans = tranService.findAll().stream().filter(tr -> accounts.contains(accService.getAccountById(tr.getSenderAccountId()))).toList();
				mv.addObject("transactions", trans );
			}else {
				mv.addObject("transactions", tranService.findAll());
			};
		});
		return mv;
	}
	
	@PostMapping("/api/transaction/createTransaction")
	public ModelAndView createTransaction(@ModelAttribute  DateModel dateModel ,@Validated @ModelAttribute Transaction transaction, BindingResult br) {
		ModelAndView mv = new ModelAndView("transactionForm");
		if(!br.hasErrors()) {
			transaction.setTransactionDate(LocalDate.now());
			String message = tranService.doTransaction(transaction);
			Account ac = accService.getAccountById(transaction.getSenderAccountId());
			double balance = ac.getAccountBalance();
			mv.addObject("message", message);
			mv.addObject("balance", balance);
		}
		
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = userService.getUserByUsername(principal);
		
		
		authorities.forEach(e -> {
			
			if(e.getAuthority().equals("USER")) {
				Customer c = customerService.getCustomerByUserId(u.getUserId());
				List<Account> accounts = accService.getAccountsByCustomerId(c.getCustomerId());
				mv.addObject("transactions", tranService.findAll().stream().filter(tr -> accounts.contains(accService.getAccountById(tr.getSenderAccountId()))).toList());
			}else {
				mv.addObject("transactions", tranService.findAll());
			};
		});
			
		
		return mv;
	}
	
	@PostMapping("/api/transaction/filterTransaction")
	public ModelAndView filterTransaction( @ModelAttribute Transaction transaction, @Validated @ModelAttribute DateModel dateModel, BindingResult br) {
		ModelAndView mv = new ModelAndView("transactionForm");
		
		var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		var principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = userService.getUserByUsername(principal);
		
		if(!br.hasErrors()) {
			authorities.forEach(e -> {
				
				if(e.getAuthority().equals("USER")) {
					Customer c = customerService.getCustomerByUserId(u.getUserId());
					List<Account> accounts = accService.getAccountsByCustomerId(c.getCustomerId());
					List<Transaction> trans = tranService.findAll().stream().filter(tr -> accounts.contains(accService.getAccountById(tr.getSenderAccountId()))).toList();
					
					List<Transaction> withinPeriod = trans.stream().filter(elem -> (dateModel.getTo().isAfter(elem.getTransactionDate()) && dateModel.getFrom().isBefore(elem.getTransactionDate()))).toList();
						System.out.println(withinPeriod);	
					mv.addObject("transactions", withinPeriod );
				}else {
					mv.addObject("transactions", tranService.findAll().stream().filter(elem -> (dateModel.getTo().isAfter(elem.getTransactionDate()) && dateModel.getFrom().isBefore(elem.getTransactionDate()))).toList());
				};
			});
		}
	
			
		
		
		return mv;
	}

}
