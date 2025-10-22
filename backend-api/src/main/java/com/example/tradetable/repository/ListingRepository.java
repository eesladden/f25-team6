package com.example.tradetable.repository;

import com.example.tradetable.entity.Listing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    //Query to search for listings by city name
    @Query("SELECT l FROM Listing l WHERE LOWER(l.cityName) LIKE LOWER(CONCAT('%', :cityName, '%'))")
    java.util.List<Listing> findByCityNameContaining(String cityName);
    //Query to search for listings by condition
    @Query("SELECT l FROM Listing l WHERE LOWER(l.condition) LIKE LOWER(CONCAT('%', :condition, '%'))")
    java.util.List<Listing> findByConditionContaining(String condition);
    //Query to search for listings by grade
    @Query("SELECT l FROM Listing l WHERE LOWER(l.grade) LIKE LOWER(CONCAT('%', :grade, '%'))")
    java.util.List<Listing> findByGradeContaining(String grade);
    //Query to search for just for sale listings
    @Query("SELECT l FROM Listing l WHERE l.isForSale = true")
    java.util.List<Listing> findForSaleListings();
    //Query to search for just for trade listings
    @Query("SELECT l FROM Listing l WHERE l.isForSale = false")
    java.util.List<Listing> findForTradeListings();
    //Query to get all listings by most recently updated or created
    @Query("SELECT l FROM Listing l ORDER BY COALESCE(l.updatedAt, l.createdAt) DESC")
    java.util.List<Listing> findAllOrderByMostRecent();
    //Query to get all available listings
    @Query("SELECT l FROM Listing l WHERE l.isAvailable = true")
    java.util.List<Listing> findAllAvailableListings();
    //Query to get all listings in order of lowest market price to highest
    @Query("SELECT l FROM Listing l ORDER BY l.marketPrice ASC")
    java.util.List<Listing> findAllOrderByMarketPriceAsc();
    //Query to get all listings in order of highest market price to lowest
    @Query("SELECT l FROM Listing l ORDER BY l.marketPrice DESC")
    java.util.List<Listing> findAllOrderByMarketPriceDesc();
    //Query to get all listings in order of lowest high price to highest
    @Query("SELECT l FROM Listing l ORDER BY l.highPrice ASC")
    java.util.List<Listing> findAllOrderByHighPriceAsc();
    //Query to get all listings in order of highest high price to lowest
    @Query("SELECT l FROM Listing l ORDER BY l.highPrice DESC")
    java.util.List<Listing> findAllOrderByHighPriceDesc();
    //Query to get all listings in order of lowest low price to highest
    @Query("SELECT l FROM Listing l ORDER BY l.lowPrice ASC")
    java.util.List<Listing> findAllOrderByLowPriceAsc();
    //Query to get all listings in order of highest low price to lowest
    @Query("SELECT l FROM Listing l ORDER BY l.lowPrice DESC")
    java.util.List<Listing> findAllOrderByLowPriceDesc();
    //Query to get listings from a specific provider_id
    @Query("SELECT l FROM Listing l WHERE l.provider.id = :providerId")
    java.util.List<Listing> findByProviderId(Long providerId);
}
