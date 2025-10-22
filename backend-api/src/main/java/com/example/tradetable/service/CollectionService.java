package com.example.tradetable.service;

import com.example.tradetable.entity.Collection;
import com.example.tradetable.repository.CollectionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public Collection getCollectionById(Long id) {
        return collectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collection not found with id " + id));
    }

    public Collection getCollectionByProviderId(Long providerId) {
        return collectionRepository.findByProviderId(providerId);
    }

    public Collection addCardToCollection(Long providerId, Long cardId) {
        return collectionRepository.addByProviderIdAndCardId(providerId, cardId);
    }
    //implement in service layer
    public void removeCardFromCollection(Long providerId, Long cardId) {
        Collection collection = collectionRepository.findByProviderId(providerId);
        if (collection == null) {
            throw new EntityNotFoundException("Collection not found for provider id " + providerId);
        }
        collection.getCards().removeIf(card -> card.getId().equals(cardId));
        collectionRepository.save(collection);
    }
}
