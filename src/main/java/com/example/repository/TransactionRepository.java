package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
