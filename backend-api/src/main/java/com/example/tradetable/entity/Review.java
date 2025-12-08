package com.example.tradetable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The provider being reviewed
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({"receivedReviews", "listings", "collection"})
    private Provider provider;

    // The customer who wrote the review
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"sentReviews"})
    private Customer customer;

    // (Optional) the listing this review is associated with
    @ManyToOne
    @JoinColumn(name = "listing_id")
    @JsonIgnoreProperties({"provider", "collection"})
    private Listing listing;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Tags like FRIENDLY, PUNCTUAL, etc.
    @ElementCollection(targetClass = ReviewTags.class)
    @CollectionTable(name = "review_tags", joinColumns = @JoinColumn(name = "review_id"))
    @Enumerated(EnumType.STRING)
    private List<ReviewTags> tags = new ArrayList<>();

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    @NotNull
    private String comment;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdAtString;

    private String providerResponse;

    private LocalDateTime responseAt;

    private String responseAtString;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.createdAtString = this.createdAt.format(formatter);
    }

    @PreUpdate
    protected void onUpdate() {
        this.responseAt = LocalDateTime.now();
        this.responseAtString = this.responseAt.format(formatter);
    }

}
