package com.example.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@RestController
public class CustomerRestController {
	@Autowired CustomerService customerService;

	@PostMapping("rest/api/customer/createCustomer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	
	@GetMapping("rest/api/customer/deleteCustomer/{customerId}")
	public void deleteCustomer( @PathVariable Long customerId ) {
		Customer c = customerService.getCustomerById(customerId);
		customerService.deleteCustomer(c);
	}
	
	@GetMapping(value = {"rest/api/customer/updateCustomer/{customerId}"})
	public Customer  updateCustomer( @PathVariable Long customerId ) {
		Customer c = customerService.getCustomerById(customerId);
		return c;
	}
	
	@GetMapping(value = {"rest/api/customer/findAll"})
	public List<Customer> findAllCustomer( ) {
		return customerService.getAllCustomers();
	}
	
}
