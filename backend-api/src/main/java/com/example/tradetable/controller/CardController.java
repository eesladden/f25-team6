package com.example.tradetable.controller;

import com.example.tradetable.entity.Card;
import com.example.tradetable.service.CardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card) {
        Card createdCard = cardService.createCard(card);
        return ResponseEntity.ok(createdCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @Valid @RequestBody Card card) {
        Card updatedCard = cardService.updateCard(id, card);
        return ResponseEntity.ok(updatedCard);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Card card = cardService.getCardById(id);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok().build();
    }

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
}