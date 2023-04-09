package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Account;
import com.example.domain.StatusType;
import com.example.domain.Transaction;
import com.example.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired AccountService accService;
	
	@Autowired TransactionRepository tranRepo;

	@Override
	public String doTransaction(Transaction t) {
		String message = "Incomplete";
		if(t.getTransactionType() != null) {
			if(t.getTransactionType().toString().equals("SEND")) {
				Account sender = accService.getAccountById(t.getSenderAccountId());
				Account receiver = accService.getAccountById(t.getReceiverAccountId());
				 if(sender != null && receiver != null) {
					if(sender.getAccountBalance() > t.getAmount()) {
						receiver.setAccountBalance(receiver.getAccountBalance()+t.getAmount());
						sender.setAccountBalance(sender.getAccountBalance()-t.getAmount());
						t.setStatus(StatusType.SUCCESS);
						tranRepo.save(t);
						message =  "success";
					}else {
						t.setStatus(StatusType.FAILURE);
						tranRepo.save(t);
						message = "Unsuccessful: Unsufficient Balance";
					}
				 }else {
					 t.setStatus(StatusType.INVALID);
						tranRepo.save(t);
						message = "Invalid: Unsuccessful transaction";
				 }
			}
			
			if(t.getTransactionType().toString().equals("WITHDRAW")) {
				Account sender = accService.getAccountById(t.getSenderAccountId());
				if(sender.getAccountBalance() > t.getAmount()) {
					sender.setAccountBalance(sender.getAccountBalance() -t.getAmount() );
					tranRepo.save(t);
					message = "success";
				}else {
					message = "Not Enough Balance";
				}
			}
			
			if(t.getTransactionType().toString().equals("DEPOSIT")) {
				Account sender = accService.getAccountById(t.getSenderAccountId());
				
					sender.setAccountBalance(sender.getAccountBalance() +t.getAmount() );
					tranRepo.save(t);
					message = "success";
				
			}
		}
		
		return message;
	}

	@Override
	public List<Transaction> findAll() {
		return tranRepo.findAll();
	}

}
