package com.example.tradetable.service;

import com.example.tradetable.entity.Listing;
import com.example.tradetable.repository.ListingRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ListingService {
    private final ListingRepository listingRepository;

    /**
     * Create a new listing.
     * @param listing the listing to create
     * @return the created listing
     */
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }
    /**
     * Update an existing listing.
     * @param id the ID of the listing to update
     * @param updatedListing the updated listing data
     * @return the updated listing
     */
    public Listing updateListing(Long id, Listing listing) {
        return listingRepository.save(listing);
    }
    /**
     * Get a listing by its ID.
     * @param id the ID of the listing
     * @return the listing
     */
    public Listing getListingById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found with id " + id));
    }
    /**
     * Get all listings ordered by various criteria.
     * @return list of listings
     */
    public java.util.List<Listing> getAllListingsOrderByMostRecent() {
        return listingRepository.findAllOrderByMostRecent();
    }
    /**
     * Get all available listings.
     * @return list of available listings
     */
    public java.util.List<Listing> getAllAvailableListings() {
        return listingRepository.findAllAvailableListings();
    }
    /**
     * Get all listings ordered by market price ascending or descending.
     * @return list of listings
     */
    public java.util.List<Listing> getAllListingsOrderByMarketPriceAsc() {
        return listingRepository.findAllOrderByMarketPriceAsc();
    }
    /**
     * Get all listings ordered by market price descending.
     * @return list of listings
     */
    public java.util.List<Listing> getAllListingsOrderByMarketPriceDesc() {
        return listingRepository.findAllOrderByMarketPriceDesc();
    }
    /**
     * Get all listings ordered by high price ascending or descending.
     * @return list of listings
     */
    public java.util.List<Listing> getAllListingsOrderByHighPriceAsc() {
        return listingRepository.findAllOrderByHighPriceAsc();
    }
    /**
     * Get all listings ordered by high price descending.
     * @return list of listings
     */
    public java.util.List<Listing> getAllListingsOrderByHighPriceDesc() {
        return listingRepository.findAllOrderByHighPriceDesc();
    }
    /**
     * Get all listings ordered by low price ascending or descending.
     * @return list of listings
     */
    public java.util.List<Listing> getAllListingsOrderByLowPriceAsc() {
        return listingRepository.findAllOrderByLowPriceAsc();
    }
    /**
     * Get all listings ordered by low price descending.
     * @return list of listings
     */
    public java.util.List<Listing> getAllListingsOrderByLowPriceDesc() {
        return listingRepository.findAllOrderByLowPriceDesc();
    }
    /**
     * Get listings by provider ID.
     * @param providerId the ID of the provider
     * @return list of listings for the provider
     */
    public java.util.List<Listing> getListingsByProvider(Long providerId) {
        return listingRepository.findByProviderId(providerId);
    }
    /**
     * Delete a listing by its ID.
     * @param id the ID of the listing to delete
     */
    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }
}