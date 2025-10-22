package com.example.tradetable.service;

import com.example.tradetable.entity.Provider;
import com.example.tradetable.repository.ProviderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ProviderService {
    private final ProviderRepository providerRepository;

    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public Provider updateProvider(Long id, Provider updatedProvider) {
        Provider existingProvider = getProviderById(id);
        existingProvider.setName(updatedProvider.getName());
        existingProvider.setEmail(updatedProvider.getEmail());
        existingProvider.setPhoneNumber(updatedProvider.getPhoneNumber());
        existingProvider.setUsername(updatedProvider.getUsername());
        // Update other fields as necessary
        return providerRepository.save(existingProvider);
    }

    //update password with validation of old password
    public Provider updateProviderPassword(Long id, String oldPassword, String newPassword) {
        Provider existingProvider = getProviderById(id);
        if (!existingProvider.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("Old password does not match");
        }
        existingProvider.setPassword(newPassword);
        return providerRepository.save(existingProvider);
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with id: " + id));
    }

    public List<Provider> searchProvidersByUsername(String username) {
        return providerRepository.findByUsernameContaining(username);
    }
}
