package com.example.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.domain.Transaction;
import com.example.service.TransactionService;

@RestController
public class TransactionRestController {

@Autowired TransactionService tranService;
	
	@PostMapping("rest/api/transaction/createTransaction")
	public ResponseEntity<String> createTransaction(@Validated @RequestBody Transaction transaction) {

		String message = tranService.doTransaction(transaction);
		if (message == "succes") return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		else return new ResponseEntity<String>("failure", HttpStatus.NOT_FOUND);
	}
}
