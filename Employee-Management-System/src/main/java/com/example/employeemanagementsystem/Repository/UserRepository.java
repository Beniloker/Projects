package com.example.employeemanagementsystem.Repository;

import com.example.employeemanagementsystem.Model.Employee;
import com.example.employeemanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByEmailOrderById(String email);

    boolean existsByEmail(String email);
}
