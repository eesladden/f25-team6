package com.example.tradetable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = true)
    @JsonIgnoreProperties({"listings", "providers"})
    private Card card;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = true)
    @JsonIgnoreProperties({"listings", "collection", "cards"})
    private Provider provider;

    @NotBlank
    private String condition;

    @NotBlank
    private String grade;

    @NotBlank
    private String marketPrice = "0.0";

    private Double mpValue;

    @NotBlank
    private String highPrice = "0.0";

    private Double hpValue;
    
    @NotBlank
    private String lowPrice = "0.0";

    private Double lpValue;

    // Indicates if the listing is still available for trade/sale
    private Boolean isAvailable = true;

    @NotBlank
    private String forSaleOrTrade = "For Sale";

    @NotBlank
    private String tradingFor = "N/A";

    @NotBlank
    private String location;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.mpValue = Double.parseDouble(this.marketPrice);
        this.hpValue = Double.parseDouble(this.highPrice);
        this.lpValue = Double.parseDouble(this.lowPrice);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.mpValue = Double.parseDouble(this.marketPrice);
        this.hpValue = Double.parseDouble(this.highPrice);
        this.lpValue = Double.parseDouble(this.lowPrice);
    }
}