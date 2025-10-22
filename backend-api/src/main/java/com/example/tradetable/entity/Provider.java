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

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private List<Listing> listings = new ArrayList<>();

    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private Collection collection;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("provider")
    private List<Review> receivedReviews = new ArrayList<>();
}


