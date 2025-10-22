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

    @PostMapping
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider) {
        Provider createdProvider = providerService.createProvider(provider);
        return ResponseEntity.ok(createdProvider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @Valid @RequestBody Provider provider) {
        Provider updatedProvider = providerService.updateProvider(id, provider);
        return ResponseEntity.ok(updatedProvider);
    }
    
    @PutMapping("/{id}/password")
    public ResponseEntity<Provider> updateProviderPassword(@PathVariable Long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Provider updatedProvider = providerService.updateProviderPassword(id, oldPassword, newPassword);
        return ResponseEntity.ok(updatedProvider);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id) {
        Provider provider = providerService.getProviderById(id);
        return ResponseEntity.ok(provider);
    }

    @PostMapping("/{providerId}/cards/{cardId}")
    public ResponseEntity<Void> addCardToCollection(@PathVariable Long providerId, @PathVariable Long cardId) {
        providerService.addCardToCollection(providerId, cardId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{providerId}/cards/{cardId}")
    public ResponseEntity<Void> removeCardFromCollection(@PathVariable Long providerId, @PathVariable Long cardId) {
        providerService.removeCardFromCollection(providerId, cardId);
        return ResponseEntity.ok().build();
    }
}
