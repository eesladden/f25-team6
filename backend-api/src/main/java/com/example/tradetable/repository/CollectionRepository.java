package com.example.tradetable.repository;

import com.example.tradetable.entity.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    @Query("SELECT c FROM Collection c WHERE c.provider.id = :providerId")
    Collection findByProviderId(Long providerId);
    @Query("SELECT c FROM Collection c JOIN c.cards card WHERE c.provider.id = :providerId AND card.id = :cardId")
    Collection addByProviderIdAndCardId(Long providerId, Long cardId);
    //not working. fix it by removing card from collection's card set and saving collection in service layer
    void removeCardFromCollection(Long providerId, Long cardId);
}
