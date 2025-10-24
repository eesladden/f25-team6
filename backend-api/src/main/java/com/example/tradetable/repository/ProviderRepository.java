package com.example.tradetable.repository;

import com.example.tradetable.entity.Provider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    boolean existsByEmail(String email);
    Optional<Provider> findByEmail(String email);
    @Query("SELECT p FROM Provider p WHERE LOWER(p.username) LIKE LOWER(CONCAT('%', :username, '%'))")
    List<Provider> findByUsernameContaining(String username);
}
