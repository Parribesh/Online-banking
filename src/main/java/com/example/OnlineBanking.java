package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OnlineBanking {

	public static void main(String[] args) {
		
		System.setProperty("server.servlet.context-path", "/banking");
		SpringApplication.run(OnlineBanking.class, args);
	}
	

//	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
//	    return new BCryptPasswordEncoder(); 
//	}

}

