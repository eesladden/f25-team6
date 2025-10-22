package com.example.tradetable.repository;

import com.example.tradetable.entity.Card;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Card> findByNameContaining(String name);
    @Query("SELECT c FROM Card c WHERE LOWER(c.deck) LIKE LOWER(CONCAT('%', :deck, '%'))")
    List<Card> findByDeckContaining(String deck);
    @Query("SELECT c FROM Card c WHERE LOWER(c.game) LIKE LOWER(CONCAT('%', :game, '%'))")
    List<Card> findByGameContaining(String game);
    @Query("SELECT c FROM Card c WHERE LOWER(c.rarity) LIKE LOWER(CONCAT('%', :rarity, '%'))")
    List<Card> findByRarityContaining(String rarity);
}
