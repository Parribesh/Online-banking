package com.example.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import com.example.validation.UserValidator;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserRestController {

	@Autowired UserService userService;
	@Autowired UserValidator userValidator;
	@Autowired RoleService roleService;

	@PostMapping("rest/api/user/createUser")
	public User createUser(@RequestParam User user ) {
		
		System.out.println("user"+ user);
		User u = userService.createUser(user);
		
		return u;
	}
	
	@GetMapping("rest/api/user/getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId){
		User u = userService.getUser(userId);
		return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = {"rest/api/user/updateUser/{userId}"})
	public User updateUser(User user, @PathVariable Long userId){
		User retrievedUser = userService.getUser(userId);
		
		return retrievedUser;
	}
	
	@RequestMapping(value = {"rest/api/user/deleteUser/{userId}"}, method = RequestMethod.GET)
	public ResponseEntity<String> deleteUser(User user, @PathVariable Long userId){
		User u = userService.deleteUserByUserId(userId);
		if(u != null) return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("failure", HttpStatus.NOT_MODIFIED);
	}
	
}
