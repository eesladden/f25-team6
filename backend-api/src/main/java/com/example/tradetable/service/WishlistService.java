package com.example.tradetable.service;

import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.Listing;
import com.example.tradetable.entity.WishlistItem;
import com.example.tradetable.repository.CustomerRepository;
import com.example.tradetable.repository.ListingRepository;
import com.example.tradetable.repository.WishlistItemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class WishlistService {

    private final WishlistItemRepository repo;
    private final CustomerRepository customerRepo;
    private final ListingRepository listingRepo;

    public WishlistService(WishlistItemRepository repo,
                           CustomerRepository customerRepo,
                           ListingRepository listingRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
        this.listingRepo = listingRepo;
    }

    /**
     * Current style (body contains a WishlistItem with listing {id}).
     * We attach the managed Customer and (optionally) the managed Listing.
     * Prevents duplicates before save, so you don't hit the unique constraint.
     */
    public WishlistItem add(Long customerId, WishlistItem item) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

        Listing listing = null;
        if (item.getListing() != null && item.getListing().getId() != null) {
            Long listingId = item.getListing().getId();
            // Duplicate guard
            if (repo.existsByCustomer_IdAndListing_Id(customerId, listingId)) {
                throw new IllegalStateException("Listing already in wishlist");
            }
            listing = listingRepo.findById(listingId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid listing ID"));
        }

        item.setCustomer(customer);
        item.setListing(listing);
        if (item.getCreatedAt() == null) item.setCreatedAt(Instant.now());

        try {
            return repo.save(item);
        } catch (DataIntegrityViolationException e) {
            // In case of a race, convert DB unique-constraint failure to a friendly message
            throw new IllegalStateException("Listing already in wishlist", e);
        }
    }

    /**
     * Convenience overload for ID-only requests:
     */
    public WishlistItem add(Long customerId, Long listingId, String cardName, String notes) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

        Listing listing = listingRepo.findById(listingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid listing ID"));

        if (repo.existsByCustomer_IdAndListing_Id(customerId, listingId)) {
            throw new IllegalStateException("Listing already in wishlist");
        }

        WishlistItem item = WishlistItem.builder()
                .customer(customer)
                .listing(listing)
                .cardName(cardName)
                .notes(notes)
                .createdAt(Instant.now())
                .build();

        try {
            return repo.save(item);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Listing already in wishlist", e);
        }
    }

    @Transactional(readOnly = true)
    public List<WishlistItem> list(Long customerId) {
        return repo.findByCustomer_Id(customerId);
    }

    @Transactional(readOnly = true)
    public List<WishlistItem> listByListing(Long listingId) {
        return repo.findByListing_Id(listingId);
    }

    /**
     * Remove by composite key (recommended from UI):
     */
    public void remove(Long customerId, Long listingId) {
        long deleted = repo.deleteByCustomer_IdAndListing_Id(customerId, listingId);
        if (deleted == 0) {
            // Silent no-op is also fine if you prefer
            throw new IllegalArgumentException("Wishlist item not found for customer/listing");
        }
    }

    /**
     * Remove by itemId with ownership check (safer than a blind delete):
     */
    public void removeByItemId(Long customerId, Long itemId) {
        WishlistItem item = repo.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist item not found"));

        if (!item.getCustomer().getId().equals(customerId)) {
            throw new SecurityException("You cannot remove another user's wishlist item");
        }
        repo.delete(item);
    }
}
