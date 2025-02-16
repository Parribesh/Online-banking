package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepo;

    public DatabaseSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepo = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(roleRepo.count() < 1){
            roleRepo.save(new Role(1L, "ADMIN"));
        }
        if(userRepository.count() < 1){
            var list = new ArrayList<Role>();
            list.add(new Role(1L, "ADMIN"));
            var passwordEncoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            var encodedPassword = passwordEncoder.encode("tester");
            var user = new User(1L, "admin", encodedPassword, "admin@tester.com", encodedPassword, list);
            // Seed the database
            userRepository.save(user);
            System.out.println("Database seeded successfully");
        }
    }
}
