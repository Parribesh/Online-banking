package com.example.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@NotNull
	private Long senderAccountId;
	
	@NotNull
	private Long receiverAccountId;
	
	private LocalDate transactionDate;
	
	private StatusType status;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@NotNull
	@Min(10)
	private double amount;
	

}
