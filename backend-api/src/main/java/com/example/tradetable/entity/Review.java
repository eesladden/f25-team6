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

@Entity
@Data
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({"receivedReviews", "listings", "collection"})
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"sentReviews"})
    private Customer customer;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private java.util.List<ReviewTags> tags;

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
