package com.example.tradetable.repository;

import com.example.tradetable.entity.Provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Provider> findByUsername(String username);
}
