package com.example.domain;

import java.time.LocalDate;



import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="account")
@NoArgsConstructor
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private Long accountId;
	
	@NotEmpty
	private String accountHolder;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	@NotNull
	private LocalDate accountDateOpened;
	
	@ManyToOne
	@JoinColumn(name="branchId")
	@NotNull
	private Branch accountBranch;
	
	@Min(10)
	private double accountBalance;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer accountCustomer;
}
