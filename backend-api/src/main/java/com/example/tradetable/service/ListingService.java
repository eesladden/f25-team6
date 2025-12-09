package com.example.tradetable.service;

import com.example.tradetable.entity.Listing;
import com.example.tradetable.repository.ListingRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ListingService {
    private final ListingRepository listingRepository;
    private final GeoApiContext geoApiContext;

    /**
     * Create a new listing.
     * @param listing the listing to create
     * @param address the address of the listing
     * @return the created listing
     */
    public Listing createListing(Listing listing, String address) {
        if(listing == null) {
            throw new IllegalArgumentException("Listing cannot be null");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        try {
            GeocodingResult[] results = com.google.maps.GeocodingApi.geocode(geoApiContext, address).await();
            if (results != null && results.length > 0) {
                listing.setLatitude((float) results[0].geometry.location.lat);
                listing.setLongitude((float) results[0].geometry.location.lng);
            } else {
                throw new IllegalArgumentException("Invalid address provided");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting address to coordinates: " + e.getMessage());
        }
        listing.setAddress(address);
        return listingRepository.save(listing);
    }
    /**
     * Update an existing listing.
     * @param id the ID of the listing to update
     * @param listing the updated listing data
     * @param address the address of the listing
     * @return the updated listing
     */
    public Listing updateListing(Long id, Listing listing, String address) {
        if(listing == null) {
            throw new IllegalArgumentException("Listing cannot be null");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        try {
            GeocodingResult[] results = com.google.maps.GeocodingApi.geocode(geoApiContext, address).await();
            if (results != null && results.length > 0) {
                listing.setLatitude((float) results[0].geometry.location.lat);
                listing.setLongitude((float) results[0].geometry.location.lng);
            } else {
                throw new IllegalArgumentException("Invalid address provided");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting address to coordinates: " + e.getMessage());
        }
        listing.setAddress(address);
        return listingRepository.save(listing);
    }
    /**
     * Get a listing by its ID.
     * @param id the ID of the listing
     * @return the listing
     */
    public Listing getListingById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Listing ID cannot be null");
        }
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
        if(id == null) {
            throw new IllegalArgumentException("Listing ID cannot be null");
        }
        listingRepository.deleteById(id);
    }
    /**
     * Mark a listing as unavailable.
     * @param id the ID of the listing to mark as unavailable
     */
    public void markListingAsUnavailable(Long id) {
        Listing listing = getListingById(id);
        listing.setIsAvailable(false);
        listingRepository.save(listing);
    }
    /**
     * Get listings by condition (exact match).
     * @param condition the condition of the listing
     * @return list of listings matching the condition criteria
     */
    public java.util.List<Listing> getListingsByCondition(String condition) {
        return listingRepository.findByCondition(condition);
    }
    /**
     * Get listings by grade (exact match).
     * @param grade the grade of the listing
     * @return list of listings matching the grade criteria
     */
    public java.util.List<Listing> getListingsByGrade(String grade) {
        return listingRepository.findByGrade(grade);
    }
    /**
     * Get listings by tradingFor (partial match).
     * @param tradingFor what the listing is trading for
     * @return list of listings matching the tradingFor criteria
     */
    public java.util.List<Listing> getListingsByTradingForContaining(String tradingFor) {
        return listingRepository.findByTradingForContaining(tradingFor);
    }
    /**
     * Search available listings by card name (partial match).
     * @param cardName the name of the card
     * @return list of available listings matching the card name
     */
    public java.util.List<Listing> searchAvailableListingsByCardName(String cardName) {
        return listingRepository.searchAvailableListingsByCardName(cardName);
    }
    /**
     * Get all available listings ordered by most recent.
     * @return list of available listings
     */    
    public java.util.List<Listing> getAllAvailableListingsOrderByMostRecent() {
        return listingRepository.findAllAvailableListingsOrderByMostRecent();
    }
    /**
     * Get all available listings ordered by market price ascending.
     * @return list of available listings
     */    
    public java.util.List<Listing> getAllAvailableListingsOrderByMarketPriceAsc() {
        return listingRepository.findAllAvailableListingsOrderByMarketPriceAsc();
    }
    /**
     * Get all available listings ordered by market price descending.
     * @return list of available listings
     */    
    public java.util.List<Listing> getAllAvailableListingsOrderByMarketPriceDesc() {
        return listingRepository.findAllAvailableListingsOrderByMarketPriceDesc();
    }
    /**
     * Get all available listings ordered by high price ascending.
     * @return list of available listings
     */    
    public java.util.List<Listing> getAllAvailableListingsOrderByHighPriceAsc() {
        return listingRepository.findAllAvailableListingsOrderByHighPriceAsc();
    }
    /**
     * Get all available listings ordered by high price descending.
     * @return list of available listings
     */    
    public java.util.List<Listing> getAllAvailableListingsOrderByHighPriceDesc() {
        return listingRepository.findAllAvailableListingsOrderByHighPriceDesc();
    }
    /**
     * Get all available listings ordered by low price ascending.
     * @return list of available listings
     */    
    public java.util.List<Listing> getAllAvailableListingsOrderByLowPriceAsc() {
        return listingRepository.findAllAvailableListingsOrderByLowPriceAsc();
    }
    /**
     * Get all available listings ordered by low price descending.
     * @return list of available listings
     */    
    public java.util.List<Listing> getAllAvailableListingsOrderByLowPriceDesc() {
        return listingRepository.findAllAvailableListingsOrderByLowPriceDesc();
    }
    /**
     * Get all available listings by provider username.
     * @param username the username of the provider
     * @return list of available listings for the provider
     */    
    public java.util.List<Listing> getAllAvailableListingsByProviderUsername(String username) {
        return listingRepository.findAllAvailableListingsByProviderUsername(username);
    }
    /**
     * Search listings by card name and provider ID.
     * @param cardName the name of the card
     * @param providerId the ID of the provider
     * @return list of listings matching the criteria
     */
    public java.util.List<Listing> searchListingsByCardNameAndProvider(String cardName, Long providerId) {
        return listingRepository.searchListingsByCardNameAndProvider(cardName, providerId);
    }

    /**
     * Get all available listings near a specific location within a given radius.
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @param radius the radius within which to search
     * @return list of available listings near the specified location
     */
    public java.util.List<Listing> getAllAvailableListingsNearLocation(double latitude, double longitude, double radius) {
        return listingRepository.findAllAvailableListingsNearLocation(latitude, longitude, radius);
    }
}