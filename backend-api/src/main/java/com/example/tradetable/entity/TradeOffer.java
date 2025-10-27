package com.example.tradetable.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "trade_offers")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TradeOffer {

    public enum Status { PENDING, ACCEPTED, DECLINED, CANCELLED }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // offer may or may not be tied to a specific listing
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id")
    @JsonIgnoreProperties({"provider","messages","collection","sentReviews","receivedReviews"})
    private Listing listing;

    // Buyer is Customer
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    @JsonIgnoreProperties({"listings","messages","collection","sentReviews","receivedReviews","wishlists"})
    private Customer buyer;

    // Seller is provider
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)   
    @JsonIgnoreProperties({"listings","messages","collection","sentReviews","receivedReviews"})
    private Provider seller;

    private Integer offeredValueCents;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private Status status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
        if (status == null) status = Status.PENDING;
    }

    @PreUpdate
    void onUpdate() { updatedAt = Instant.now(); }
}

