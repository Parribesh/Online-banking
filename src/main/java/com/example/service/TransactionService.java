package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Transaction;

@Service
public interface TransactionService {
	public String doTransaction(Transaction t);

	public List<Transaction> findAll();
}
