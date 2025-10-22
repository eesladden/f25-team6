package com.example.tradetable.controller;

import com.example.tradetable.entity.Listing;
import com.example.tradetable.service.ListingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;

    @PostMapping
    public ResponseEntity<Listing> createListing(@Valid @RequestBody Listing listing) {
        Listing createdListing = listingService.createListing(listing);
        return ResponseEntity.ok(createdListing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Long id, @Valid @RequestBody Listing listing) {
        Listing updatedListing = listingService.updateListing(id, listing);
        return ResponseEntity.ok(updatedListing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        Listing listing = listingService.getListingById(id);
        return ResponseEntity.ok(listing);
    }

    @GetMapping("/search")
    public ResponseEntity<java.util.List<Listing>> searchListings(@RequestParam(required = false) String cityName,
                                                                 @RequestParam(required = false) String condition,
                                                                 @RequestParam(required = false) String grade) {
        java.util.List<Listing> results = new java.util.ArrayList<>();
        if (cityName != null) {
            results.addAll(listingService.searchListingsByCityName(cityName));
        }
        if (condition != null) {
            results.addAll(listingService.searchListingsByCondition(condition));
        }
        if (grade != null) {
            results.addAll(listingService.searchListingsByGrade(grade));
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/for-sale")
    public ResponseEntity<java.util.List<Listing>> getForSaleListings() {
        java.util.List<Listing> listings = listingService.getForSaleListings();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/for-trade")
    public ResponseEntity<java.util.List<Listing>> getForTradeListings() {
        java.util.List<Listing> listings = listingService.getForTradeListings();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/most-recent")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByMostRecent() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByMostRecent();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/available")
    public ResponseEntity<java.util.List<Listing>> getAllAvailableListings() {
        java.util.List<Listing> listings = listingService.getAllAvailableListings();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/market-price/asc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByMarketPriceAsc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByMarketPriceAsc();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/market-price/desc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByMarketPriceDesc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByMarketPriceDesc();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/high-price/asc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByHighPriceAsc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByHighPriceAsc();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/high-price/desc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByHighPriceDesc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByHighPriceDesc();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/low-price/asc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByLowPriceAsc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByLowPriceAsc();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/low-price/desc")
    public ResponseEntity<java.util.List<Listing>> getAllListingsOrderByLowPriceDesc() {
        java.util.List<Listing> listings = listingService.getAllListingsOrderByLowPriceDesc();
        return ResponseEntity.ok(listings);
    }
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<java.util.List<Listing>> getListingsByProviderId(@PathVariable Long providerId) {
        java.util.List<Listing> listings = listingService.getListingsByProviderId(providerId);
        return ResponseEntity.ok(listings);
    }
}