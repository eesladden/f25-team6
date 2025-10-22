package com.example.tradetable.repository;

import com.example.tradetable.entity.Review;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProviderId(Long providerId);
    //Query to get reviews by rating highest to lowest
    @Query("SELECT r FROM Review r WHERE r.provider.id = :providerId ORDER BY r.rating DESC")
    List<Review> findByProviderIdOrderByRatingDesc(Long providerId);
    //Query to get reviews by rating lowest to highest
    @Query("SELECT r FROM Review r WHERE r.provider.id = :providerId ORDER BY r.rating ASC")
    List<Review> findByProviderIdOrderByRatingAsc(Long providerId);
    //Query to find review by comment substring
    @Query("SELECT r FROM Review r WHERE r.provider.id = :providerId AND r.comment LIKE %:substring%")
    List<Review> findByProviderIdAndCommentContaining(Long providerId, String substring);
    //query to delete only sent reviews by provider id and review id
    void deleteByIdAndProviderId(Long id, Long providerId);

}
