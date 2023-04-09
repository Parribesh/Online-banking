package com.example.domain;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;



@Entity
@Table(name="user")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	@NotEmpty
	private String username;
	@NotEmpty
	private String userPassword;
	@NotEmpty
	private String userEmail;
	@NotEmpty
	private String userMobile;
	
	@ManyToMany(fetch=FetchType.EAGER)
	//go for a join table
	
	@NotNull
	List<Role> userRoles = new ArrayList<>();
	
	
	public User(Long userId, String username, String password, String email, String mobile, List<Role> roles){
		this.userId = userId;
		this.username = username;
		this.userPassword = password;
		this.userEmail = email;
		this.userMobile = mobile;
		this.userRoles = roles;

	}
	

	
	

}
