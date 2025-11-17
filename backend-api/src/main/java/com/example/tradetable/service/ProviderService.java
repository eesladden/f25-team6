package com.example.tradetable.service;

import com.example.tradetable.entity.Provider;
import com.example.tradetable.repository.ProviderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ProviderService {
    private final ProviderRepository providerRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/profile-pictures/";
    
    /**
     * Create a new provider.
     * @param provider the provider to create
     * @param imageFile the profile image file
     * @return the created provider
     */
    public Provider createProvider(Provider provider, MultipartFile imageFile) {
        if (providerRepository.existsByUsername(provider.getUsername())) {
            throw new IllegalStateException("Username already registered");
        }
        if (providerRepository.existsByEmail(provider.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        
        Provider newProvider = providerRepository.save(provider);
        String originalFileName = imageFile.getOriginalFilename();

        try {
            if (originalFileName != null && originalFileName.contains(".")) {
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = String.valueOf(newProvider.getId()) + fileExtension;
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                InputStream inputStream = imageFile.getInputStream();

                Files.createDirectories(Paths.get(UPLOAD_DIR));
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                newProvider.setProfileImagePath(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return providerRepository.save(newProvider);
    }
    /**
     * Authenticate a provider.
     * @param username
     * @param password
     * @return
     */
    public Provider authenticate(String username, String password) {
        Provider provider = providerRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!provider.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return provider;
    }
    /**
     * Update an existing provider.
     * @param id the ID of the provider to update
     * @param updatedProvider the updated provider data
     * @return the updated provider
     */
    public Provider updateProvider(Long id, Provider provider, MultipartFile imageFile) {
        if(provider == null) {
            throw new IllegalArgumentException("Provider ID cannot be null");
        }
        String originalFileName = imageFile.getOriginalFilename();
        
        try {
            if (originalFileName != null && originalFileName.contains(".")) {
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = String.valueOf(id) + fileExtension;
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                InputStream inputStream = imageFile.getInputStream();

                Files.createDirectories(Paths.get(UPLOAD_DIR));
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                provider.setProfileImagePath(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return providerRepository.save(provider);
    }
    /**
     * Increment the listings listed count for a provider.
     * @param providerId the ID of the provider
     */
    public void incrementListingsListed(Long providerId) {
        Provider provider = getProviderById(providerId);
        provider.setListingsListed(provider.getListingsListed() + 1);
        providerRepository.save(provider);
    }
    /**
     * Increment the collection size for a provider.
     * @param providerId the ID of the provider
     */
    public void incrementCollectionSize(Long providerId) {
        Provider provider = getProviderById(providerId);
        provider.setCollectionSize(provider.getCollectionSize() + 1);
        providerRepository.save(provider);
    }
    /**
     * Increment the trades completed count for a provider.
     * @param providerId the ID of the provider
     */
    public void incrementTradesCompleted(Long providerId) {
        Provider provider = getProviderById(providerId);
        provider.setTradesCompleted(provider.getTradesCompleted() + 1);
        providerRepository.save(provider);
    }
    /**
     * Decrement the collection size for a provider.
     * @param providerId the ID of the provider
     */
    public void decrementCollectionSize(Long providerId) {
        Provider provider = getProviderById(providerId);
        provider.setCollectionSize(provider.getCollectionSize() - 1);
        providerRepository.save(provider);
    }
    /**
     * Decrement the listings listed count for a provider.
     * @param providerId the ID of the provider
     */
    public void decrementListingsListed(Long providerId) {
        Provider provider = getProviderById(providerId);
        provider.setListingsListed(provider.getListingsListed() - 1);
        providerRepository.save(provider);
    }
    /**
     * Update provider password.
     * @param id the ID of the provider to update
     * @param oldPassword the old password
     * @param newPassword the new password
     * @return the updated provider
     */
    public Provider updateProviderPassword(Long id, String currentPassword, String newPassword) {
        Provider existingProvider = getProviderById(id);
        if (!existingProvider.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("Current password does not match");
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
        if(id == null) {
            throw new IllegalArgumentException("Provider ID cannot be null");
        }
        return providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with id: " + id));
    }
    /**
     * Delete a provider by ID.
     * @param id the ID of the provider to delete
     */
    public void deleteProviderById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Provider ID cannot be null");
        }
        providerRepository.deleteById(id);
    }
}