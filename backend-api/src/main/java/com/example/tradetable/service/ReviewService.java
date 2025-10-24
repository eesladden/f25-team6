package com.example.tradetable.service;

import com.example.tradetable.entity.Review;
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

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id " + id));
    }

    public List<Review> getReviewsByProviderId(Long providerId) {
        return reviewRepository.findByProviderId(providerId);
    }

    public List<Review> getReviewsByProviderIdAndCommentSubstring(Long providerId, String substring) {
        return reviewRepository.findByProviderIdAndCommentContaining(providerId, substring);
    }

    public List<Review> getReviewsByProviderIdOrderByRatingDesc(Long providerId) {
        return reviewRepository.findByProviderIdOrderByRatingDesc(providerId);
    }

    public List<Review> getReviewsByProviderIdOrderByRatingAsc(Long providerId) {
        return reviewRepository.findByProviderIdOrderByRatingAsc(providerId);
    }
    
    public void deleteReview(Long id) {
        Review review = getReviewById(id);
        Long providerId = review.getProvider().getId();
        reviewRepository.deleteByIdAndProviderId(id, providerId);
    }
}
