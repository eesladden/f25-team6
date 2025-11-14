package com.example.tradetable.repository;

import com.example.tradetable.entity.Listing;

//import com.example.tradetable.entity.Listing; unused import
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;
@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    @Query("SELECT l FROM Listing l WHERE LOWER(l.cityName) LIKE LOWER(CONCAT('%', :cityName, '%'))")
    java.util.List<Listing> findByCityNameContaining(String cityName);
    @Query("SELECT l FROM Listing l WHERE LOWER(l.condition) LIKE LOWER(CONCAT('%', :condition, '%'))")
    java.util.List<Listing> findByConditionContaining(String condition);
    @Query("SELECT l FROM Listing l WHERE LOWER(l.grade) LIKE LOWER(CONCAT('%', :grade, '%'))")
    java.util.List<Listing> findByGradeContaining(String grade);
    @Query("SELECT l FROM Listing l WHERE l.isForSale = true")
    java.util.List<Listing> findForSaleListings();
    @Query("SELECT l FROM Listing l WHERE l.isForSale = false")
    java.util.List<Listing> findForTradeListings();
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

    // --- helpers (no @Query needed) ---
    List<Listing> findAllByProvider_Id(Long providerId);              
    List<Listing> findAllByIsForSaleTrue();                           
    List<Listing> findAllByIsForSaleFalse();                          
    List<Listing> findAllByIsAvailableTrue();                         

    // Useful for “recent listings” without writing JPQL — assume updatedAt may be null
    List<Listing> findAllByIsAvailableTrueOrderByUpdatedAtDesc();    

    // Batch fetch by IDs 
    List<Listing> findByIdIn(Collection<Long> ids);

    // Simple pagination hook for any filter
    List<Listing> findAllByIsAvailableTrue(Pageable pageable);
}
