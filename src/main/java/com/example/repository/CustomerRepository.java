package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer getCustomerByCustomerId(Long id);
	public Customer deleteCustomerByCustomerId(Long id);
	public Customer findByUser_UserId(Long userId);
	
}
