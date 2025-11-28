package com.example.tradetable.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Data
@NoArgsConstructor
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({"messages", "sentReviews", "receivedReviews", "listings", "collection"})
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"messages", "sentReviews", "receivedReviews", "listings", "collection"})
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    @JsonIgnoreProperties({"conversations"})
    private Listing listing;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"conversation"})
    private List<Message> messages;

    @Column(updatable = false)
    private LocalDateTime lastUpdated;

    private String lastUpdatedString;

    @PrePersist
    protected void onCreate() {
        this.lastUpdated = LocalDateTime.now();
        this.lastUpdatedString = this.lastUpdated.format(formatter);
    }
}
