package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Account;
import com.example.domain.Customer;
import com.example.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired AccountRepository accountRepo;
	@Autowired CustomerService customerService;
	
	@Override
	public Account createAccount(Account account) {
		
		return accountRepo.save(account);
	}

	@Override
	public Account getAccountById(Long accountId) {
		if(accountId != null)
		return accountRepo.findById(accountId).orElse(null);
		else return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		
		return accountRepo.findAll();
	}

	@Override
	public void deleteAccount(Account account) {
		accountRepo.delete(account);

	}

	@Override
	public List<Account> getAccountsByCustomerId(Long customerId) {
		Customer c = customerService.getCustomerById(customerId);
		List<Account> accounts = c.getCustomerAccounts();
		return accounts;
	}

}
