package com.example.tradetable.repository;

import com.example.tradetable.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUsername(String username);
}
