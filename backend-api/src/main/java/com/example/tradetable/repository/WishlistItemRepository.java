package com.example.tradetable.repository;

import com.example.tradetable.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByCustomer_Id(Long customerId);
    boolean existsByCustomer_IdAndListingId(Long customerId, Long listingId);
}
