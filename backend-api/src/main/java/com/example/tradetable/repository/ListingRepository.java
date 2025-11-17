package com.example.tradetable.repository;

import com.example.tradetable.entity.Listing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    @Query("SELECT l FROM Listing l WHERE LOWER(l.condition) LIKE LOWER(CONCAT('%', :condition, '%'))")
    java.util.List<Listing> findByConditionContaining(String condition);
    @Query("SELECT l FROM Listing l WHERE LOWER(l.grade) LIKE LOWER(CONCAT('%', :grade, '%'))")
    java.util.List<Listing> findByGradeContaining(String grade);
    @Query("SELECT l FROM Listing l ORDER BY COALESCE(l.updatedAt, l.createdAt) DESC")
    java.util.List<Listing> findAllOrderByMostRecent();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true")
    java.util.List<Listing> findAllAvailableListings();
    @Query("SELECT l FROM Listing l ORDER BY l.marketPrice ASC")
    java.util.List<Listing> findAllOrderByMarketPriceAsc();
    @Query("SELECT l FROM Listing l ORDER BY l.marketPrice DESC")
    java.util.List<Listing> findAllOrderByMarketPriceDesc();
    @Query("SELECT l FROM Listing l ORDER BY l.highPrice ASC")
    java.util.List<Listing> findAllOrderByHighPriceAsc();
    @Query("SELECT l FROM Listing l ORDER BY l.highPrice DESC")
    java.util.List<Listing> findAllOrderByHighPriceDesc();
    @Query("SELECT l FROM Listing l ORDER BY l.lowPrice ASC")
    java.util.List<Listing> findAllOrderByLowPriceAsc();
    @Query("SELECT l FROM Listing l ORDER BY l.lowPrice DESC")
    java.util.List<Listing> findAllOrderByLowPriceDesc();
    @Query("SELECT l FROM Listing l WHERE l.provider.id = :providerId")
    java.util.List<Listing> findByProviderId(Long providerId);
    
    //For MVC
    @Query("SELECT l FROM Listing l WHERE LOWER(l.condition) = LOWER(:condition)")
    java.util.List<Listing> findByCondition(String condition);
    @Query("SELECT l FROM Listing l WHERE LOWER(l.grade) = LOWER(:grade)")
    java.util.List<Listing> findByGrade(String grade);
    @Query("SELECT l FROM Listing l WHERE LOWER(l.tradingFor) LIKE LOWER(CONCAT('%', :tradingFor, '%'))")
    java.util.List<Listing> findByTradingForContaining(String tradingFor);
    @Query("SELECT l FROM Listing l WHERE LOWER(l.card.name) LIKE LOWER(CONCAT('%', :cardName, '%')) AND l.isAvailable = true")
    java.util.List<Listing> searchAvailableListingsByCardName(String cardName);
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true ORDER BY COALESCE(l.updatedAt, l.createdAt) DESC")
    java.util.List<Listing> findAllAvailableListingsOrderByMostRecent();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true ORDER BY l.marketPrice ASC")
    java.util.List<Listing> findAllAvailableListingsOrderByMarketPriceAsc();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true ORDER BY l.marketPrice DESC")
    java.util.List<Listing> findAllAvailableListingsOrderByMarketPriceDesc();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true ORDER BY l.highPrice ASC")
    java.util.List<Listing> findAllAvailableListingsOrderByHighPriceAsc();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true ORDER BY l.highPrice DESC")
    java.util.List<Listing> findAllAvailableListingsOrderByHighPriceDesc();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true ORDER BY l.lowPrice ASC")
    java.util.List<Listing> findAllAvailableListingsOrderByLowPriceAsc();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true ORDER BY l.lowPrice DESC")
    java.util.List<Listing> findAllAvailableListingsOrderByLowPriceDesc();
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true AND l.provider.username = :username")
    java.util.List<Listing> findAllAvailableListingsByProviderUsername(String username);
    @Query("SELECT l FROM Listing l WHERE LOWER(l.card.name) LIKE LOWER(CONCAT('%', :cardName, '%')) AND l.provider.id = :providerId")
    java.util.List<Listing> searchListingsByCardNameAndProvider(String cardName, Long providerId);
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true AND LOWER(l.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    java.util.List<Listing> findAllAvailableListingsByLocation(String location);
}