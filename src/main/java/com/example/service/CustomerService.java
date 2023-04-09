package com.example.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.example.domain.Customer;


@Service
public interface CustomerService {
	
	public Customer createCustomer(Customer customer);
	public Customer getCustomerById(Long id);
	public List<Customer> getAllCustomers();
	public void deleteCustomer(Customer customer);
	public Customer getCustomerByUserId(long userId);
	
}
