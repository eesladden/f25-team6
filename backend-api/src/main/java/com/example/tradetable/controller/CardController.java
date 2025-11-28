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
     * Search cards by name.
     * @param name the name to search for
     * @return list of cards matching the name
     */
    @GetMapping("/search")
    public ResponseEntity<java.util.List<Card>> searchCardsByName(@RequestParam String name) {
        java.util.List<Card> cards = cardService.searchCardsByName(name);
        return ResponseEntity.ok(cards);
    }
    /**
     * Filter cards by game, set, or rarity.
     * @param game the game to filter by
     * @param set the set to filter by
     * @param rarity the rarity to filter by
     * @return list of filtered cards
     */
    @GetMapping("/filter")
    public ResponseEntity<java.util.List<Card>> filterCards(
            @RequestParam(required = false) String game,
            @RequestParam(required = false) String set,
            @RequestParam(required = false) String rarity) {
        java.util.List<Card> cards;
        if (game != null) {
            cards = cardService.filterCardsByGame(game);
        } else if (set != null) {
            cards = cardService.filterCardsBySet(set);
        } else if (rarity != null) {
            cards = cardService.filterCardsByRarity(rarity);
        } else {
            cards = cardService.getAllCards();
        }
        return ResponseEntity.ok(cards);
    }
    /**
     * Add a card to a provider's collection.
     * @param cardId the ID of the card
     * @param providerId the ID of the provider
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
    /**
     * Get all unique games.
     * @return list of unique games
     */
    @GetMapping("/unique/games")
    public ResponseEntity<java.util.List<String>> findAllUniqueGames() {
        java.util.List<String> games = cardService.findAllUniqueGames();
        return ResponseEntity.ok(games);
    }
    /**
     * Get all unique sets.
     * @return list of unique sets
     */
    @GetMapping("/unique/sets")
    public ResponseEntity<java.util.List<String>> findAllUniqueSets() {
        java.util.List<String> sets = cardService.findAllUniqueSets();
        return ResponseEntity.ok(sets);
    }
    /**
     * Get all unique rarities.
     * @return list of unique rarities
     */
    @GetMapping("/unique/rarities")
    public ResponseEntity<java.util.List<String>> findAllUniqueRarities() {
        java.util.List<String> rarities = cardService.findAllUniqueRarities();
        return ResponseEntity.ok(rarities);
    }
}