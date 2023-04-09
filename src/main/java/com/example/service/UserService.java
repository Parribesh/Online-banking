package com.example.service;

import java.util.List;

import com.example.domain.User;


public interface UserService {
	public User createUser(User u);
	public User getUser(Long userId);
	public User deleteUserByUserId(Long userId);
	public User updateUser(User user);
	public List<User> findAll();
	public User getUserByUsername(String username);
}
