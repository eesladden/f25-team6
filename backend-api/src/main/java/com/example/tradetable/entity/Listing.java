package com.example.tradetable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    @JsonIgnoreProperties("listings")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties("listings")
    private Provider provider;

    @NotBlank
    private String condition;

    @NotBlank
    private String grade;

    private Double marketPrice = 0.0;

    private Double highPrice = 0.0;

    private Double lowPrice = 0.0;
    
    //should be true when new listing is created
    private Boolean isAvailable = true;

    private Boolean isForSale; // true for sale, false for trade

    private String tradingFor = "N/A";

    @NotBlank
    private String cityName;

    @NotBlank
    private String imageLink;

    @Column(updatable = false)
    private java.time.LocalDateTime createdAt;

    private java.time.LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = java.time.LocalDateTime.now();
        this.updatedAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = java.time.LocalDateTime.now();
    }
}
