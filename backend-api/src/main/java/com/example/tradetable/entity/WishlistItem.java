package com.example.tradetable.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
@Entity
@Table(
    name = "wishlist_items",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_wishlist_customer_listing", columnNames = {"customer_id", "listing_id"})
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each wishlist belongs to a Customer
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    // Link to a Listing (owned by a Provider)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id")
    @JsonIgnoreProperties({"provider", "messages", "collection", "sentReviews", "receivedReviews"})
    private Listing listing;

    // Optional extra info
    private String cardName;  // fallback if not linked to listing
    private String notes;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
    }
}
