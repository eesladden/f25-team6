package com.example.tradetable.service;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer signup(Customer incoming) {
        if (incoming.getEmail() == null || incoming.getEmail().isBlank())
            throw new IllegalArgumentException("Email required");
        if (incoming.getUsername() == null || incoming.getUsername().isBlank())
            throw new IllegalArgumentException("Username required");
        if (incoming.getPassword() == null || incoming.getPassword().isBlank())
            throw new IllegalArgumentException("Password required");

        if (repo.existsByEmail(incoming.getEmail()))
            throw new IllegalArgumentException("Email already in use");
        if (repo.existsByUsername(incoming.getUsername()))
            throw new IllegalArgumentException("Username already in use");

        String hash = BCrypt.hashpw(incoming.getPassword(), BCrypt.gensalt());
        incoming.setPasswordHash(hash);
        incoming.setPassword(null); // extra safety in memory
        return repo.save(incoming);
    }

    @Transactional(readOnly = true)
    public Customer get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    public Customer updateProfile(Long id, Customer patch) {
        Customer c = get(id);
        if (patch.getDisplayName() != null) c.setDisplayName(patch.getDisplayName());
        if (patch.getBio() != null) c.setBio(patch.getBio());
        if (patch.getAvatarUrl() != null) c.setAvatarUrl(patch.getAvatarUrl());
        if (patch.getPreferredSets() != null) c.setPreferredSets(patch.getPreferredSets());
        if (patch.getShippingRegion() != null) c.setShippingRegion(patch.getShippingRegion());
        return c; // JPA dirty checking will save on tx commit
    }

    @Transactional(readOnly = true)
    public List<Customer> listAll() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public Customer findByUsernameOrEmail(String usernameOrEmail) {
        return repo.findByUsername(usernameOrEmail)
                   .or(() -> repo.findByEmail(usernameOrEmail))
                   .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional(readOnly = true)
    public boolean checkLogin(String usernameOrEmail, String rawPassword) {
        Customer c = findByUsernameOrEmail(usernameOrEmail);
        return BCrypt.checkpw(rawPassword, c.getPasswordHash());
    }
}
