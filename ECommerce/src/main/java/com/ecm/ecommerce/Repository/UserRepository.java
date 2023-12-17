package com.ecm.ecommerce.Repository;

import com.ecm.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByEmailOrderById(String email);

    boolean existsByEmail(String email);
}
