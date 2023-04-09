package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserJdbcRepository;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired UserRepository userRepo;
	@Autowired UserJdbcRepository userJdbcRepository;
	@Autowired BCryptPasswordEncoder bCrypt;

	@Override
	public User createUser(User u) {
		String password = u.getUserPassword();
		String encodedPassowrd = bCrypt.encode(password);
		u.setUserPassword(encodedPassowrd);
		User returnedUser = userRepo.save(u);
		return returnedUser;
	}

	@Override
	public User getUser(Long userId) {
//		return userJdbcRepository.getUser(userId);
		return userRepo.findUserByUserId(userId);
		
	}

	@Override
	public User deleteUserByUserId(Long userId) {
		User u = userRepo.findUserByUserId(userId);	
		userRepo.delete(u);
		return u;
	}


	@Override
	public User updateUser(User user) {
	
		User toUpdate = userRepo.findByUserId(user.getUserId());
		toUpdate.setUsername(user.getUsername());
		toUpdate.setUserPassword(user.getUserPassword());
		toUpdate.setUserEmail(user.getUserEmail());
		toUpdate.setUserMobile(user.getUserMobile());
	
		return userRepo.save(toUpdate);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User getUserByUsername(String username) {
		
		return userRepo.findUserByUsername(username);
	}

}
