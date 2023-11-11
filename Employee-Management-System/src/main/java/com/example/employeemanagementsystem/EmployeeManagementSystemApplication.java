package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.Model.DTOs.RegisterDTO;
import com.example.employeemanagementsystem.Model.User;
import com.example.employeemanagementsystem.Security.Authentication.AuthenticationService;
import com.example.employeemanagementsystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeManagementSystemApplication implements CommandLineRunner {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        authenticationService.createUser(new RegisterDTO("Luke", "Skywalker", "luke@gmail.com", "123456"));
        authenticationService.createUser(new RegisterDTO("Pumba", "Warthog", "pumba@gmail.com", "123456"));
        authenticationService.createUser(new RegisterDTO("Harry", "Potter", "harry@gmail.com", "123456"));

        LocalDate joinDate = LocalDate.of(2021, 1, 1);
        User admin = new User("Admin", "One", "admin@gmail.com", passwordEncoder.encode("654321"), joinDate, "ADMIN");
        userService.updateUser(admin);
    }
}