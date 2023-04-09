package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
