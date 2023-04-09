package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findUserByUsername(String username);
	public User findUserByUserId(Long userId);
	public User deleteUserByUserId(Long userId);
	public User findByUserId(Long userId);


}
