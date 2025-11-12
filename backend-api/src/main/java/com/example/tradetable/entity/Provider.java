//Need to be able to map multiple providers to a single card and multiple cards to a single provider for the collection list
package com.example.tradetable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    //Collection needs to take the provider-card table and find all cards associated with the provider ID and make a list of them
    @ManyToMany(mappedBy = "providers")
    @JsonIgnoreProperties({"providers", "listings", "messages", "sentReviews", "receivedReviews"})
    private java.util.Set<Card> collection = new java.util.HashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"provider", "messages", "collection", "sentReviews", "receivedReviews"})
    private List<Listing> listings = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"provider", "messages", "collection", "sentReviews", "receivedReviews"})
    private List<Review> receivedReviews = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"provider", "messages", "collection", "sentReviews", "receivedReviews"})
    private List<Message> messages = new ArrayList<>();
}


