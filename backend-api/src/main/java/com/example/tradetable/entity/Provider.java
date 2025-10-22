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
//Provider should have a name, unique username, unique email, unique phone number, password, and birthdate.
//Provider should also contain foreign key references to Listing, Card, and Message entities.
//A list of created listings, collected (collection) cards, and sent/received messages can be included as relationships.
//A list of reviews received from Customers can also be included as a relationship.
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

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private List<Listing> listings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "provider_cards",
        joinColumns = @JoinColumn(name = "provider_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    @JsonIgnoreProperties("owners")
    private List<Card> collection = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private List<Message> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private List<Review> receivedReviews = new ArrayList<>();
}


