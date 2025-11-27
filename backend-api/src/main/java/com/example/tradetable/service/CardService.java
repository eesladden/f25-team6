package com.example.tradetable.service;

import com.example.tradetable.entity.Card;
import com.example.tradetable.repository.CardRepository;

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
public class CardService {
    private final CardRepository cardRepository;

    private static final String UPLOAD_DIR = "backend-api/src/main/resources/static/card-images/";

    /**
     * Create a new card.
     * @param card the card to create
     * @param imageFile the image file for the card
     * @return the created card
     */
    public Card createCard(Card card, MultipartFile imageFile) {
        if(card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        Card newCard = cardRepository.save(card);
        String originalFileName = imageFile.getOriginalFilename();

        try {
            if (originalFileName != null && originalFileName.contains(".")) {
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = String.valueOf(newCard.getId()) + fileExtension;
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                InputStream inputStream = imageFile.getInputStream();

                Files.createDirectories(Paths.get(UPLOAD_DIR));
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                newCard.setImagePath(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardRepository.save(newCard);
    }
    /**
     * Get a card by ID.
     * @param id the ID of the card
     * @return the card with the specified ID
     */
    public Card getCardById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Card ID cannot be null");
        }
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
    //Get cards by provider
    public List<Card> getCardsByProvider(Long providerId) {
        return cardRepository.getCardsByProvider(providerId);
    }
    /**
     * Delete a card by ID.
     * @param id the ID of the card to delete
     */
    public void deleteCard(Long id) {
        Card existingCard = getCardById(id);
        if(existingCard == null) {
            throw new EntityNotFoundException("Card not found with id " + id);
        }
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
    public List<Card> filterCardsByGame(String game) {
        return cardRepository.findByGame(game);
    }
    public List<Card> filterCardsBySet(String set) {
        return cardRepository.findBySet(set);
    }
    public List<Card> filterCardsByRarity(String rarity) {
        return cardRepository.findByRarity(rarity);
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
    /**
     * Get all unique games.
     * @return list of unique games
     */
    public List<String> findAllUniqueGames() {
        return cardRepository.findAllUniqueGames();
    }
    /**
     * Get all unique sets.
     * @return list of unique sets
     */
    public List<String> findAllUniqueSets() {
        return cardRepository.findAllUniqueSets();
    }
    /**
     * Get all unique rarities.
     * @return list of unique rarities
     */
    public List<String> findAllUniqueRarities() {
        return cardRepository.findAllUniqueRarities();
    }
}