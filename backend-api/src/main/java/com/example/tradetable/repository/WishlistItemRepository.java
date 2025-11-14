package com.example.tradetable.repository;

import com.example.tradetable.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

    List<WishlistItem> findByCustomer_Id(Long customerId);
    List<WishlistItem> findByListing_Id(Long listingId);

    boolean existsByCustomer_IdAndListing_Id(Long customerId, Long listingId);
    Optional<WishlistItem> findByCustomer_IdAndListing_Id(Long customerId, Long listingId);

    long deleteByCustomer_IdAndListing_Id(Long customerId, Long listingId);

    // Retrieves all wishlist items for a specific customer, ordered by creation time descending
    List<WishlistItem> findAllByCustomer_IdOrderByCreatedAtDesc(Long customerId);

    // Counts wishlist items for a specific customer
    long countByCustomer_Id(Long customerId);
}
