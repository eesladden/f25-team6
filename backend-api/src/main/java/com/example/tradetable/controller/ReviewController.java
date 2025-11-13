package com.example.tradetable.controller;

import com.example.tradetable.entity.Review;
import com.example.tradetable.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ConditionalOnExpression("${my.controller.enabled:false}")
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/{id}/response")
    public ResponseEntity<Review> respondToReview(@PathVariable Long id, @Valid @RequestBody Review updatedReview) {
        Review respondedReview = reviewService.respondToReview(id, updatedReview);
        return ResponseEntity.ok(respondedReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<java.util.List<Review>> getReviewsByCustomerId(@PathVariable Long customerId) {
        java.util.List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<java.util.List<Review>> getReviewsByProviderId(@PathVariable Long providerId) {
        java.util.List<Review> reviews = reviewService.getReviewsByProviderId(providerId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/provider/{providerId}/sort")
    public ResponseEntity<java.util.List<Review>> getReviewsByProviderIdSorted(
            @PathVariable Long providerId, @RequestParam String order) {
        java.util.List<Review> reviews;
        if ("asc".equalsIgnoreCase(order)) {
            reviews = reviewService.getReviewsByProviderIdOrderByRatingAsc(providerId);
        } else {
            reviews = reviewService.getReviewsByProviderIdOrderByRatingDesc(providerId);
        }
        return ResponseEntity.ok(reviews);
    }
    
    @GetMapping("/provider/{providerId}/search")
    public ResponseEntity<java.util.List<Review>> getReviewsByProviderIdAndCommentSubstring(
            @PathVariable Long providerId, @RequestParam String substring) {
        java.util.List<Review> reviews = reviewService.getReviewsByProviderIdAndCommentSubstring(providerId, substring);
        return ResponseEntity.ok(reviews);
    }
}
