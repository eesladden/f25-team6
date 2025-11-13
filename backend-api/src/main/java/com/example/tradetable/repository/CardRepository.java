package com.example.tradetable.repository;

import com.example.tradetable.entity.Card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Card> findByNameContaining(String name);
    @Query("SELECT c FROM Card c WHERE LOWER(c.set) LIKE LOWER(CONCAT('%', :deck, '%'))")
    List<Card> findByDeckContaining(String deck);
    @Query("SELECT c FROM Card c WHERE LOWER(c.game) LIKE LOWER(CONCAT('%', :game, '%'))")
    List<Card> findByGameContaining(String game);
    @Query("SELECT c FROM Card c WHERE LOWER(c.rarity) LIKE LOWER(CONCAT('%', :rarity, '%'))")
    List<Card> findByRarityContaining(String rarity);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO collections (card_id, provider_id) VALUES (:cardId, :providerId)", nativeQuery = true)
    void addCardToProviderCollection(@Param("cardId") Long cardId, @Param("providerId") Long providerId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM collections WHERE card_id = :cardId AND provider_id = :providerId", nativeQuery = true)
    void removeCardFromProviderCollection(@Param("cardId") Long cardId, @Param("providerId") Long providerId);
    @Query("SELECT c FROM Card c JOIN c.providers p WHERE p.id = :providerId")
    List<Card> getCardsByProvider(@Param("providerId") Long providerId);
}
