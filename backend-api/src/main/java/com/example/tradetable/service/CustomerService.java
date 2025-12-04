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

    // ----------------------------------------------------
    // SIGN UP
    // ----------------------------------------------------
    public Customer signup(Customer incoming) {
        // Basic required fields
        if (incoming.getEmail() == null || incoming.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email required");
        }
        if (incoming.getUsername() == null || incoming.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username required");
        }
        if (incoming.getPhoneNumber() == null || incoming.getPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("Phone number required");
        }
        if (incoming.getPassword() == null || incoming.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password required");
        }

        // Uniqueness checks
        if (repo.existsByEmail(incoming.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (repo.existsByUsername(incoming.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }
        if (repo.existsByPhoneNumber(incoming.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already in use");
        }

        // Hash password and clear plain-text
        String hash = BCrypt.hashpw(incoming.getPassword(), BCrypt.gensalt());
        incoming.setPasswordHash(hash);
        incoming.setPassword(null); // extra safety in memory

        return repo.save(incoming);
    }

    // ----------------------------------------------------
    // BASIC FETCHES
    // ----------------------------------------------------
    @Transactional(readOnly = true)
    public Customer get(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    @Transactional(readOnly = true)
    public List<Customer> listAll() {
        return repo.findAll();
    }

    // ----------------------------------------------------
    // PROFILE UPDATE (INCLUDING EMAIL/PHONE)
    // ----------------------------------------------------
    public Customer updateProfile(Long id, Customer patch) {
        Customer existing = get(id);

        // Email update with uniqueness check
        if (patch.getEmail() != null && !patch.getEmail().isBlank()
                && !patch.getEmail().equals(existing.getEmail())) {

            repo.findByEmail(patch.getEmail()).ifPresent(other -> {
                if (!other.getId().equals(id)) {
                    throw new IllegalArgumentException("Email already in use");
                }
            });
            existing.setEmail(patch.getEmail());
        }

        // Phone update with uniqueness check
        if (patch.getPhoneNumber() != null && !patch.getPhoneNumber().isBlank()
                && !patch.getPhoneNumber().equals(existing.getPhoneNumber())) {

            repo.findByPhoneNumber(patch.getPhoneNumber()).ifPresent(other -> {
                if (!other.getId().equals(id)) {
                    throw new IllegalArgumentException("Phone number already in use");
                }
            });
            existing.setPhoneNumber(patch.getPhoneNumber());
        }

        // Other profile fields (only apply if non-null)
        if (patch.getDisplayName() != null) {
            existing.setDisplayName(patch.getDisplayName());
        }
        if (patch.getBio() != null) {
            existing.setBio(patch.getBio());
        }
        if (patch.getPreferredSets() != null) {
            existing.setPreferredSets(patch.getPreferredSets());
        }
        if (patch.getShippingRegion() != null) {
            existing.setShippingRegion(patch.getShippingRegion());
        }

        // ✅ profileImageUrl from profile edit form
        if (patch.getProfileImageUrl() != null) {
            existing.setProfileImageUrl(patch.getProfileImageUrl());
        }

        // No explicit save needed; JPA dirty checking will persist on tx commit
        return existing;
    }

    // ----------------------------------------------------
    // PASSWORD UPDATE
    // ----------------------------------------------------
    public void updatePassword(Long id, String newPassword) {
        if (newPassword == null || newPassword.isBlank()) {
            throw new IllegalArgumentException("Password required");
        }

        Customer existing = get(id);

        String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        existing.setPasswordHash(hash);
        // JPA dirty checking will flush this change
    }

    // ----------------------------------------------------
    // LOGIN HELPERS (EMAIL / PHONE / USERNAME)
    // ----------------------------------------------------
    @Transactional(readOnly = true)
    public Customer findByIdentifier(String identifier) {
        // Try email first
        return repo.findByEmail(identifier)
                .or(() -> repo.findByPhoneNumber(identifier))  // then phone
                .or(() -> repo.findByUsername(identifier))     // then username
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Backwards-compatible method if any old code still calls it.
     * Prefer findByIdentifier for new code.
     */
    @Transactional(readOnly = true)
    public Customer findByUsernameOrEmail(String usernameOrEmail) {
        return repo.findByUsername(usernameOrEmail)
                .or(() -> repo.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional(readOnly = true)
    public boolean checkLogin(String identifier, String rawPassword) {
        Customer c = findByIdentifier(identifier);
        return BCrypt.checkpw(rawPassword, c.getPasswordHash());
    }

    // ----------------------------------------------------
    // DELETE
    // ----------------------------------------------------
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
