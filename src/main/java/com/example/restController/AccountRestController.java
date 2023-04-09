package com.example.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Account;
import com.example.service.AccountService;

@RestController
public class AccountRestController {
	
@Autowired AccountService accountService;
	
	@PostMapping("rest/api/account/createAccount")
	public Account createAccount(@Validated @RequestBody Account account ) {	
		return accountService.createAccount(account);
	}
	
	@GetMapping("rest/api/account/updateAccount/{accountId}")
	public Account updateAccount( @PathVariable Long accountId){
		Account a = accountService.getAccountById(accountId);
		return a;
	}
	
	@GetMapping("rest/api/account/deleteAccount/{accountId}")
	public void deleteAccount( @PathVariable Long accountId){
		Account a = accountService.getAccountById(accountId);
		accountService.deleteAccount(a);
	}
	
	@GetMapping("rest/api/account/findAll")
	public List<Account> findAllAccount(){
		List<Account> accounts = accountService.getAllAccounts();
		return accounts;
	}
	

}
