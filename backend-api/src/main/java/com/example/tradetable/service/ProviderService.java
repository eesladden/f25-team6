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
    /**
     * Create a new provider.
     * @param provider the provider to create
     * @return the created provider
     */
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }
    /**
     * Update an existing provider.
     * @param id the ID of the provider to update
     * @param updatedProvider the updated provider data
     * @return the updated provider
     */
    public Provider updateProvider(Long id, Provider updatedProvider) {
        Provider existingProvider = getProviderById(id);
        existingProvider.setName(updatedProvider.getName());
        existingProvider.setEmail(updatedProvider.getEmail());
        existingProvider.setPhoneNumber(updatedProvider.getPhoneNumber());
        existingProvider.setUsername(updatedProvider.getUsername());
        return providerRepository.save(existingProvider);
    }
    /**
     * Update provider password.
     * @param id the ID of the provider to update
     * @param oldPassword the old password
     * @param newPassword the new password
     * @return the updated provider
     */
    public Provider updateProviderPassword(Long id, String oldPassword, String newPassword) {
        Provider existingProvider = getProviderById(id);
        if (!existingProvider.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("Old password does not match");
        }
        existingProvider.setPassword(newPassword);
        return providerRepository.save(existingProvider);
    }
    /**
     * Get all providers.
     * @return list of providers
     */
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }
    /**
     * Get a provider by ID.
     * @param id the ID of the provider
     * @return the provider
     */
    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with id: " + id));
    }
    /**
     * Search providers by username.
     * @param username the username to search by
     * @return list of matching providers
     */
    public List<Provider> searchProvidersByUsername(String username) {
        return providerRepository.findByUsernameContaining(username);
    }
}
