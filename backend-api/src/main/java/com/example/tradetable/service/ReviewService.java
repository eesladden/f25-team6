package com.example.tradetable.service;

import com.example.tradetable.entity.Review;
import com.example.tradetable.entity.ReviewTags;
import com.example.tradetable.entity.Customer;
import com.example.tradetable.entity.Provider;
import com.example.tradetable.repository.ReviewRepository;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    // add these if you want service to handle IDs -> entities
    private final CustomerService customerService;
    private final ProviderService providerService;

    /**
     * Creates a new review.
     * @param review the review to create
     * @return the created review
     * @throws IllegalArgumentException if the review is null
     */
    public Review createReview(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        return reviewRepository.save(review);
    }

    // Customer-friendly overload: create a Review from IDs + fields
    public Review createReview(Long customerId,
                               Long providerId,
                               int rating,
                               String comment,
                               List<String> tags) {

        if (customerId == null || providerId == null) {
            throw new IllegalArgumentException("Customer and provider IDs are required");
        }

        Customer customer = customerService.get(customerId);
        Provider provider = providerService.getProviderById(providerId);

        Review review = new Review();
        review.setCustomer(customer);
        review.setProvider(provider);
        review.setRating(rating);
        review.setComment(comment);

        // Convert String tags -> ReviewTags enum list, safely
        if (tags != null && !tags.isEmpty()) {
            List<ReviewTags> tagEnums = new ArrayList<>();
            for (String name : tags) {
                if (name == null) continue;
                try {
                    tagEnums.add(ReviewTags.valueOf(name.toUpperCase()));
                } catch (IllegalArgumentException ignored) {
                    // ignore invalid tag names instead of crashing
                }
            }
            review.setTags(tagEnums);
        }

        return reviewRepository.save(review);
    }

    /**
     * Responds to a review by adding a provider response.
     * @param id the id of the review to respond to
     * @param response the provider's response
     * @return the updated review
     * @throws EntityNotFoundException if the review is not found
     */
    public Review respondToReview(Long id, String response) {
        Review review = getReviewById(id);
        review.setProviderResponse(response);
        return reviewRepository.save(review);
    }
    /**
     * Retrieves a review by its ID.
     * @param id the ID of the review
     * @return the review with the specified ID
     * @throws IllegalArgumentException if the ID is null
     * @throws EntityNotFoundException if the review is not found
     */
    public Review getReviewById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Review id cannot be null");
        }
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id " + id));
    }
    /**
     * Retrieves reviews by provider ID.
     * @param providerId the ID of the provider
     * @return a list of reviews for the specified provider
     */
    public List<Review> getReviewsByProviderId(Long providerId) {
        return reviewRepository.findByProviderId(providerId);
    }
    /**
     * Retrieves reviews by customer ID.
     * @param customerId the ID of the customer
     * @return a list of reviews for the specified customer
     */
    public List<Review> getReviewsByCustomerId(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }
    /**
     * Retrieves reviews by provider ID containing a specific substring in the comment.
     * @param providerId the ID of the provider
     * @param substring the substring to search for in comments
     * @return a list of reviews matching the criteria
     */
    public List<Review> getReviewsByProviderIdAndCommentSubstring(Long providerId, String substring) {
        return reviewRepository.findByProviderIdAndCommentContaining(providerId, substring);
    }
    /**
     * Retrieves reviews by provider ID ordered by rating in descending order.
     * @param providerId the ID of the provider
     * @return a list of reviews ordered by rating descending
     */
    public List<Review> getReviewsByProviderIdOrderByRatingDesc(Long providerId) {
        return reviewRepository.findByProviderIdOrderByRatingDesc(providerId);
    }
    /**
     * Retrieves reviews by provider ID ordered by rating in ascending order.
     * @param providerId the ID of the provider
     * @return a list of reviews ordered by rating ascending
     */
    public List<Review> getReviewsByProviderIdOrderByRatingAsc(Long providerId) {
        return reviewRepository.findByProviderIdOrderByRatingAsc(providerId);
    }
    /**
     * Deletes a review by its ID.
     * @param id the ID of the review to delete
     * @throws EntityNotFoundException if the review is not found
     */
    public void deleteReview(Long id) {
        Review review = getReviewById(id);
        Long providerId = review.getProvider().getId();
        reviewRepository.deleteByIdAndProviderId(id, providerId);
    }
    /**
     * Retrieves reviews by provider ID and rating.
     * @param providerId the ID of the provider
     * @param rating the rating to filter by
     * @return a list of reviews matching the provider ID and rating
     */
    public List<Review> getReviewsByProviderIdAndRating(Long providerId, int rating) {
        return reviewRepository.findByProviderIdAndRating(providerId, rating);
    }
    /**
     * Retrieves reviews by provider ID ordered by creation date in descending order.
     * @param providerId the ID of the provider
     * @return a list of reviews ordered by creation date descending
     */
    public List<Review> getReviewsByProviderIdOrderByCreatedAtDesc(Long providerId) {
        return reviewRepository.findByProviderIdOrderByCreatedAtDesc(providerId);
    }
    /**
     * Retrieves reviews by provider ID ordered by creation date in ascending order.
     * @param providerId the ID of the provider
     * @return a list of reviews ordered by creation date ascending
     */
    public List<Review> getReviewsByProviderIdOrderByCreatedAtAsc(Long providerId) {
        return reviewRepository.findByProviderIdOrderByCreatedAtAsc(providerId);
    }
    /**
     * Retrieves reviews by provider ID and tag name.
     * @param providerId the ID of the provider
     * @param tagName the name of the tag to filter by
     * @return a list of reviews matching the provider ID and tag name
     */
    public List<Review> getReviewsByProviderIdAndTagName(Long providerId, String tagName) {
        List<Review> allReviews = reviewRepository.findByProviderId(providerId);
        List<Review> filteredReviews = new java.util.ArrayList<>();
        for (Review review : allReviews) {
            List<ReviewTags> tags = review.getTags();
            for (ReviewTags tag : tags) {
                if (tag.name().equals(tagName)) {
                    filteredReviews.add(review);
                    break;
                }
            }
        }
        return filteredReviews;
    }
    /**
     * Updates the overall rating of a provider based on their reviews.
     * @param providerId the ID of the provider
     */
    public void updateProviderOverallRating(Long providerId) {
        List<Review> reviews = getReviewsByProviderId(providerId);
        double total = 0.0;
        for (Review review : reviews) {
            total += review.getRating();
        }
        double overallRating = total / reviews.size();
        providerService.updateOverallRating(providerId, overallRating);
    }
}
