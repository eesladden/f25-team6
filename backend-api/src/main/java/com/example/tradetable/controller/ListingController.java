package com.example.tradetable.controller;

import com.example.tradetable.entity.Listing;
import com.example.tradetable.service.ListingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ConditionalOnExpression("${my.controller.enabled:false}")
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;
    /**
     * Create a new listing.
     *
     * @param listing the listing to create
     * @return the created listing
     */
    @PostMapping
    public ResponseEntity<Listing> createListing(@Valid @RequestBody Listing listing, @RequestParam Long providerId, @RequestParam Long cardId) {
        Listing createdListing = listingService.createListing(listing, providerId, cardId);
        return ResponseEntity.ok(createdListing);
    }
    /**
     * Update an existing listing.
     * @param id the id of the listing to update
     * @param listing the updated listing data
     * @return the updated listing
     */
    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Long id, @Valid @RequestBody Listing listing) {
        Listing updatedListing = listingService.updateListing(id, listing);
        return ResponseEntity.ok(updatedListing);
    }
    /**
     * Get a listing by its ID.
     * @param id the ID of the listing
     * @return the listing
     */
    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        Listing listing = listingService.getListingById(id);
        return ResponseEntity.ok(listing);
    }
    /**
     * Get listings that are for sale.
     * @return list of listings for sale
     */
    //@GetMapping("/for-sale")
    //public ResponseEntity<java.util.List<Listing>> getForSaleListings() {
    //    java.util.List<Listing> listings = listingService.getForSaleListings();
    //    return ResponseEntity.ok(listings);
    //}
    /**
     * Get listings that are for trade.
     * @return list of listings for trade
     */
    //@GetMapping("/for-trade")
    //public ResponseEntity<java.util.List<Listing>> getForTradeListings() {
    //    java.util.List<Listing> listings = listingService.getForTradeListings();
    //    return ResponseEntity.ok(listings);
    //}
    /**
     * Get all listings ordered by various criteria.
     * @return list of listings
     */
    @GetMapping("/most-recent")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByMostRecent() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByMostRecent();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get all available listings.
     * @return list of available listings
     */
    @GetMapping("/available")
    public ResponseEntity<java.util.List<Listing>> getAllAvailableListings() {
        java.util.List<Listing> listings = listingService.getAllAvailableListings();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get all listings ordered by market price ascending.
     * @return list of listings ordered by market price ascending
     */
    @GetMapping("/market-price/asc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByMarketPriceAsc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByMarketPriceAsc();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get all listings ordered by market price descending.
     * @return list of listings ordered by market price descending
     */
    @GetMapping("/market-price/desc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByMarketPriceDesc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByMarketPriceDesc();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get all listings ordered by high price ascending.
     * @return list of listings ordered by high price ascending
     */
    @GetMapping("/high-price/asc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByHighPriceAsc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByHighPriceAsc();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get all listings ordered by high price descending.
     * @return list of listings ordered by high price descending
     */
    @GetMapping("/high-price/desc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByHighPriceDesc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByHighPriceDesc();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get all listings ordered by low price ascending.
     * @return list of listings ordered by low price ascending
     */
    @GetMapping("/low-price/asc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByLowPriceAsc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByLowPriceAsc();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get all listings ordered by low price descending.
     * @return list of listings ordered by low price descending
     */
    @GetMapping("/low-price/desc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByLowPriceDesc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByLowPriceDesc();
        return ResponseEntity.ok(listings);
    }
    /**
     * Get listings by provider ID.
     * @param providerId the ID of the provider
     * @return list of listings by the provider
     */
    //@GetMapping("/provider/{providerId}")
    //public ResponseEntity<java.util.List<Listing>> getListingsByProviderId(@PathVariable Long providerId) {
    //    java.util.List<Listing> listings = listingService.getListingsByProviderId(providerId);
    //    return ResponseEntity.ok(listings);
    //}
}