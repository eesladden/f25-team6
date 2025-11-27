package com.example.tradetable.repository;

import com.example.tradetable.entity.TradeOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeOfferRepository extends JpaRepository<TradeOffer, Long> {

    
    List<TradeOffer> findByBuyer_Id(Long buyerId);
    List<TradeOffer> findBySeller_Id(Long sellerId);
    List<TradeOffer> findByListingId(Long listingId);

    
    // Retrieves all trade offers made by a specific customer, ordered by creation time (newest first)
    List<TradeOffer> findAllByBuyer_IdOrderByCreatedAtDesc(Long buyerId);

    // Counts how many offers a specific customer has made
    long countByBuyer_Id(Long buyerId);
}
