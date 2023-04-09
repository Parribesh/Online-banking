	package com.example.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Customer {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;
	
	@NotEmpty
	private String customerFirstName;
	
	@NotEmpty
	private String customerLastName;
	
	@NotEmpty
	private String customerGender;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate customerDOB;
	
	private String customerMobileNumber;
	
	@Embedded
	@Valid
	private Address customerAddress;
	
	@NotEmpty
	private  String customerSSN;
	
	@OneToMany(mappedBy = "accountCustomer")
	private List<Account> customerAccounts = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	public Customer() {
		
	}
	
	public Customer(String firstname,String lastname, String gender, LocalDate dob, String phone, Address add, String ssn ) {

		this.customerGender = gender;
		this.customerFirstName = firstname;
		this.customerLastName = lastname;
		this.customerDOB = dob;
		this.customerMobileNumber = phone;
		this.customerAddress = add;
		this.customerSSN = ssn;
		
		
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public LocalDate getCustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(LocalDate customerDOB) {
		this.customerDOB = customerDOB;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerSSN() {
		return customerSSN;
	}

	public void setCustomerSSN(String customerSSN) {
		this.customerSSN = customerSSN;
	}

	public List<Account> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(List<Account> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", customerGender=" + customerGender + ", customerDOB=" + customerDOB
				+ ", customerMobileNumber=" + customerMobileNumber + ", customerAddress=" + customerAddress
				+ ", customerSSN=" + customerSSN + ", customerAccounts=" + customerAccounts + ", user=" + user + "]";
	}
	

	
}
