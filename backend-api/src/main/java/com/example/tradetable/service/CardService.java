package com.example.tradetable.service;

import com.example.tradetable.entity.Card;
import com.example.tradetable.repository.CardRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class CardService {
    private final CardRepository cardRepository;

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card updatedCard) {
        Card existingCard = getCardById(id);
        existingCard.setName(updatedCard.getName());
        existingCard.setDeck(updatedCard.getDeck());
        existingCard.setGame(updatedCard.getGame());
        existingCard.setRarity(updatedCard.getRarity());
        // Update other fields as necessary
        return cardRepository.save(existingCard);
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id " + id));
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public void deleteCard(Long id) {
        Card existingCard = getCardById(id);
        cardRepository.delete(existingCard);
    }

    public List<Card> searchCardsByName(String name) {
        return cardRepository.findByNameContaining(name);
    }

    public List<Card> searchCardsByDeck(String deck) {
        return cardRepository.findByDeckContaining(deck);
    }

    public List<Card> searchCardsByGame(String game) {
        return cardRepository.findByGameContaining(game);
    }
    
    public List<Card> searchCardsByRarity(String rarity) {
        return cardRepository.findByRarityContaining(rarity);
    }
}
