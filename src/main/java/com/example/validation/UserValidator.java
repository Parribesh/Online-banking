package com.example.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User u =(User)(target);
		
		if(u.getUsername().length() == 0) {
//			errors.rejectValue("username", "user.name", "Username should not be empty");
		}
		
	}

}
