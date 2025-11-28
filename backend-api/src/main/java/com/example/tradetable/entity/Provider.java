//Need to be able to map multiple providers to a single card and multiple cards to a single provider for the collection list
package com.example.tradetable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String phoneNumber;

    @NotBlank
    private String password;

    @NotBlank
    private String birthdate;

    private String profileImagePath;

    private String bio;

    private String bgImagePath = "tcg-bg.jpg";

    private Double overallRating;

    @Column(updatable = false)
    private String yearCreated;
    
    @Min(0)
    private Integer listingsListed = 0;

    @Min(0)
    private Integer tradesCompleted = 0;

    @Min(0)
    private Integer collectionSize = 0;

    @PrePersist
    protected void onCreate() {
        this.yearCreated = String.valueOf(java.time.Year.now().getValue());
    }

    @ManyToMany(mappedBy = "providers")
    @JsonIgnoreProperties({"providers", "listings", "receivedReviews"})
    private java.util.Set<Card> collection = new java.util.HashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"provider", "collection", "receivedReviews"})
    private List<Listing> listings = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"provider", "listings", "collection"})
    private List<Review> receivedReviews = new ArrayList<>();
}


