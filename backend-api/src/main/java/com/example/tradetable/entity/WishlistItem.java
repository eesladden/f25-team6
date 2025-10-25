package com.example.tradetable.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
@Entity
@Table(name = "wishlist_items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WishlistItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore 
    private Customer customer;

    // Listing entity, convert this to @ManyToOne Listing
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = true)
    @JsonIgnoreProperties({"customer", "sentReviews", "receivedReviews", "listings", "messages", "collection"})
    private Listing listing;

    private String cardName;    // fallback if no listing
    private String notes;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() { createdAt = Instant.now(); }
}
