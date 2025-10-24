package com.example.tradetable.controller;

import com.example.tradetable.entity.Provider;
import com.example.tradetable.service.ProviderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    /**
     * Create a new provider.
     * @param provider the provider to create
     * @return
     */
    @PostMapping
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider) {
        Provider createdProvider = providerService.createProvider(provider);
        return ResponseEntity.ok(createdProvider);
    }
    /**
     * Update an existing provider.
     * @param id the ID of the provider to update
     * @param provider the updated provider data
     * @return the updated provider
     */
    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @Valid @RequestBody Provider provider) {
        Provider updatedProvider = providerService.updateProvider(id, provider);
        return ResponseEntity.ok(updatedProvider);
    }
    /**
     * Update provider password.
     * @param id the ID of the provider to update
     * @param oldPassword the old password
     * @param newPassword the new password
     * @return the updated provider
     */
    @PutMapping("/{id}/password")
    public ResponseEntity<Provider> updateProviderPassword(@PathVariable Long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Provider updatedProvider = providerService.updateProviderPassword(id, oldPassword, newPassword);
        return ResponseEntity.ok(updatedProvider);
    }
    /**
     * Get a provider by ID.
     * @param id the ID of the provider
     * @return the provider
     */
    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id) {
        Provider provider = providerService.getProviderById(id);
        return ResponseEntity.ok(provider);
    }
}
