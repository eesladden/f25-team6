package com.example.tradetable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Sender sender;
    private Recipient recipient;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = true)
    @JsonIgnoreProperties({"messages", "sentReviews", "receivedReviews", "listings", "collection"})
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    @JsonIgnoreProperties({"messages", "sentReviews", "receivedReviews", "listings", "collection"})
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    @JsonIgnoreProperties({"messages"})
    private Conversation conversation;

    @NotBlank
    private String content;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private String createdAtString;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.createdAtString = this.createdAt.format(formatter);
    }
}
