package com.example.tradetable.entity;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.time.Instant;
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
@Entity
@Table(name = "customers",
       uniqueConstraints = {
         @UniqueConstraint(name="uk_customers_email", columnNames="email"),
         @UniqueConstraint(name="uk_customers_username", columnNames="username")
       })
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotBlank
    @Column(nullable = false, length = 255)
    private String email;

    @NotBlank @Size(min = 3, max = 30)
    @Column(nullable = false, length = 30)
    private String username;

    @NotBlank @Size(min = 2, max = 60)
    @Column(nullable = false, length = 60)
    private String displayName;

    @Column(length = 500)
    private String bio;

    private String avatarUrl;
    private String preferredSets;
    private String shippingRegion;

    /** Store only the hash in DB; never return to users */
    @JsonIgnore
    @Column(nullable = false, length = 72)
    private String passwordHash;

    /** Accept raw password ONLY on input; never serialize out */
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

    @Column(length = 4096)
    private String ProfileImageUrl;

    @Column(unique = true, length = 30)
    private String phoneNumber; 
}
