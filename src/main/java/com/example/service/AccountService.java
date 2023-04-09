package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Account;

@Service
public interface AccountService {
	
	public Account createAccount(Account account);
	public Account getAccountById(Long accountId);
	public List<Account> getAllAccounts();
	public void deleteAccount(Account account);
	public List<Account> getAccountsByCustomerId(Long customerId);

}
