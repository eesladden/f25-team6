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

    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public Listing updateListing(Long id, Listing updatedListing) {
        Listing existingListing = getListingById(id);
        existingListing.setCityName(updatedListing.getCityName());
        existingListing.setCondition(updatedListing.getCondition());
        existingListing.setGrade(updatedListing.getGrade());
        existingListing.setIsForSale(updatedListing.getIsForSale());
        existingListing.setIsAvailable(updatedListing.getIsAvailable());
        existingListing.setMarketPrice(updatedListing.getMarketPrice());
        existingListing.setHighPrice(updatedListing.getHighPrice());
        existingListing.setLowPrice(updatedListing.getLowPrice());
        // Update other fields as necessary
        return listingRepository.save(existingListing);
    }

    public Listing getListingById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found with id " + id));
    }
    public java.util.List<Listing> searchListingsByCityName(String cityName) {
        return listingRepository.findByCityNameContaining(cityName);
    }
    public java.util.List<Listing> searchListingsByCondition(String condition) {
        return listingRepository.findByConditionContaining(condition);
    }
    public java.util.List<Listing> searchListingsByGrade(String grade) {
        return listingRepository.findByGradeContaining(grade);
    }
    public java.util.List<Listing> getForSaleListings() {
        return listingRepository.findForSaleListings();
    }
    public java.util.List<Listing> getForTradeListings() {
        return listingRepository.findForTradeListings();
    }
    public java.util.List<Listing> getAllListingsOrderByMostRecent() {
        return listingRepository.findAllOrderByMostRecent();
    }
    public java.util.List<Listing> getAllAvailableListings() {
        return listingRepository.findAllAvailableListings();
    }
    public java.util.List<Listing> getAllListingsOrderByMarketPriceAsc() {
        return listingRepository.findAllOrderByMarketPriceAsc();
    }
    public java.util.List<Listing> getAllListingsOrderByMarketPriceDesc() {
        return listingRepository.findAllOrderByMarketPriceDesc();
    }
    public java.util.List<Listing> getAllListingsOrderByHighPriceAsc() {
        return listingRepository.findAllOrderByHighPriceAsc();
    }
    public java.util.List<Listing> getAllListingsOrderByHighPriceDesc() {
        return listingRepository.findAllOrderByHighPriceDesc();
    }
    public java.util.List<Listing> getAllListingsOrderByLowPriceAsc() {
        return listingRepository.findAllOrderByLowPriceAsc();
    }
    public java.util.List<Listing> getAllListingsOrderByLowPriceDesc() {
        return listingRepository.findAllOrderByLowPriceDesc();
    }
    public java.util.List<Listing> getListingsByProviderId(Long providerId) {
        return listingRepository.findByProviderId(providerId);
    }
}
