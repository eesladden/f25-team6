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
    /**
     * Create a new card.
     * @param card the card to create
     * @return the created card
     */
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }
    /**
     * Update an existing card.
     * @param id   the ID of the card to update
     * @param updatedCard the updated card data
     * @return the updated card
     */
    public Card updateCard(Long id, Card updatedCard) {
        Card existingCard = getCardById(id);
        existingCard.setName(updatedCard.getName());
        existingCard.setDeck(updatedCard.getDeck());
        existingCard.setGame(updatedCard.getGame());
        existingCard.setRarity(updatedCard.getRarity());
        // Update other fields as necessary
        return cardRepository.save(existingCard);
    }
    /**
     * Get a card by ID.
     * @param id the ID of the card
     * @return the card with the specified ID
     */
    public Card getCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id " + id));
    }
    /**
     * Get all cards.
     * @return list of all cards
     */
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
    /**
     * Delete a card by ID.
     * @param id the ID of the card to delete
     */
    public void deleteCard(Long id) {
        Card existingCard = getCardById(id);
        cardRepository.delete(existingCard);
    }
    /**
     * Search cards by name.
     * @param name the name to search for
     * @return list of cards matching the name
     */
    public List<Card> searchCardsByName(String name) {
        return cardRepository.findByNameContaining(name);
    }
    /**
     * Search cards by deck.
     * @param deck the deck to search for
     * @return list of cards matching the deck
     */
    public List<Card> searchCardsByDeck(String deck) {
        return cardRepository.findByDeckContaining(deck);
    }
    /**
     * Search cards by game.
     * @param game the game to search for
     * @return list of cards matching the game
     */
    public List<Card> searchCardsByGame(String game) {
        return cardRepository.findByGameContaining(game);
    }
    /**
     * Search cards by rarity.
     * @param rarity the rarity to search for
     * @return list of cards matching the rarity
     */
    public List<Card> searchCardsByRarity(String rarity) {
        return cardRepository.findByRarityContaining(rarity);
    }
    /**
     * Add a card to a provider's collection.
     * @param cardId the ID of the card
     * @param providerId the ID of the provider
     */
    public void addCardToProviderCollection(Long cardId, Long providerId) {
        cardRepository.addCardToProviderCollection(cardId, providerId);
    }
    /**
     * Remove a card from a provider's collection.
     * @param cardId the ID of the card
     * @param providerId the ID of the provider
     */
    public void removeCardFromProviderCollection(Long cardId, Long providerId) {
        cardRepository.removeCardFromProviderCollection(cardId, providerId);
    }
}
