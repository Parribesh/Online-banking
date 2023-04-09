package com.example.validation;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.domain.Role;

@Component
public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Role r = (Role)target;
		
		if(r.getRoleName().length() == 0) {
			errors.rejectValue("roleName", "role.name", "Role name cannot be Empty!");
		}
		
	}

	

}
