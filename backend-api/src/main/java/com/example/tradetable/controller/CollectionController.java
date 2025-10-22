package com.example.tradetable.controller;

import com.example.tradetable.entity.Collection;
import com.example.tradetable.service.CollectionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping("/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable Long id) {
        Collection collection = collectionService.getCollectionById(id);
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<Collection> getCollectionByProviderId(@PathVariable Long providerId) {
        Collection collection = collectionService.getCollectionByProviderId(providerId);
        return ResponseEntity.ok(collection);
    }

    @PostMapping("/provider/{providerId}/cards/{cardId}")
    public ResponseEntity<Collection> addCardToCollection(@PathVariable Long providerId, @PathVariable Long cardId) {
        Collection updatedCollection = collectionService.addCardToCollection(providerId, cardId);
        return ResponseEntity.ok(updatedCollection);
    }

    @DeleteMapping("/provider/{providerId}/cards/{cardId}")
    public ResponseEntity<Void> removeCardFromCollection(@PathVariable Long providerId, @PathVariable Long cardId) {
        collectionService.removeCardFromCollection(providerId, cardId);
        return ResponseEntity.ok().build();
    }
}
