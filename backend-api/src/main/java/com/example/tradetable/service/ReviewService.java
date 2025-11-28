package com.example.tradetable.service;

import com.example.tradetable.entity.Review;
import com.example.tradetable.entity.ReviewTags;
import com.example.tradetable.repository.ReviewRepository;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
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
    //findByProviderIdAndTagName
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
}
