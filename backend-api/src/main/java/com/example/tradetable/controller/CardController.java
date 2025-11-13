package com.example.tradetable.controller;

import com.example.tradetable.entity.Card;
import com.example.tradetable.service.CardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ConditionalOnExpression("${my.controller.enabled:false}")
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    /**
     * Get all cards.
     *
     * @return list of all cards
     */
    @GetMapping
    public ResponseEntity<java.util.List<Card>> getAllCards() {
        java.util.List<Card> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }
    /**
     * Create a new card.
     * @param card the card to create
     * @return the created card
     */
    @PostMapping
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card, @RequestParam(required = false) MultipartFile imageFile) {
        Card createdCard = cardService.createCard(card, imageFile);
        return ResponseEntity.ok(createdCard);
    }
    /**
     * Update an existing card.
     * @param id   the ID of the card to update
     * @param card the updated card data
     * @return the updated card
     */
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @Valid @RequestBody Card card, @RequestParam(required = false) MultipartFile imageFile) {
        Card updatedCard = cardService.updateCard(id, card, imageFile);
        return ResponseEntity.ok(updatedCard);
    }
    /**
     * Get a card by ID.
     * @param id the ID of the card
     * @return the card with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Card card = cardService.getCardById(id);
        return ResponseEntity.ok(card);
    }
    /**
     * Delete a card by ID.
     * @param id the ID of the card to delete
     * @return response entity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok().build();
    }
    /**
     * Search cards by various criteria.
     * @param name   the name of the card
     * @param deck   the deck of the card
     * @param game   the game of the card
     * @param rarity the rarity of the card
     * @return list of cards matching the search criteria
     */
    @GetMapping("/search")
    public ResponseEntity<java.util.List<Card>> searchCards(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String deck,
                                                          @RequestParam(required = false) String game,
                                                          @RequestParam(required = false) String rarity) {
        java.util.List<Card> results = new java.util.ArrayList<>();
        if (name != null) {
            results.addAll(cardService.searchCardsByName(name));
        }
        if (deck != null) {
            results.addAll(cardService.searchCardsByDeck(deck));
        }
        if (game != null) {
            results.addAll(cardService.searchCardsByGame(game));
        }
        if (rarity != null) {
            results.addAll(cardService.searchCardsByRarity(rarity));
        }
        return ResponseEntity.ok(results);
    }
    /**
     * Add a card to a provider's collection.
     * @param cardId the ID of the card
     * @param providerId
     */
    @PostMapping("/{cardId}/providers/{providerId}")
    public void addCardToProviderCollection(@PathVariable Long cardId, @PathVariable Long providerId) {
        cardService.addCardToProviderCollection(cardId, providerId);
    }
    /**
     * Remove a card from a provider's collection.
     * @param cardId the ID of the card
     * @param providerId the ID of the provider
     */
    @DeleteMapping("/{cardId}/providers/{providerId}")
    public void removeCardFromProviderCollection(@PathVariable Long cardId, @PathVariable Long providerId) {
        cardService.removeCardFromProviderCollection(cardId, providerId);
    }
}