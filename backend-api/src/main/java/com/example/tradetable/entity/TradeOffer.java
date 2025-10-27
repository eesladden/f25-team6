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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = true)
    @JsonIgnoreProperties({"customer", "sentReviews", "receivedReviews", "listings", "messages", "collection"})
    private Listing listing;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Customer buyer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    @JsonIgnoreProperties({"customer", "sentReviews", "receivedReviews", "listings", "messages", "collection"})
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
    void onUpdate() {
        updatedAt = Instant.now();
    }
}
