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
@Table(name = "messages")
//Message should include an auto-generated id number, a foreign key reference to the Provider who received the message, a foreign key reference to the Customer who sent the message, the message content, and a timestamp for creation.
//And the Provider could also be the sender and the Customer the receiver.
//With sender and receiver role names for the linking to a list of sent and received messages.
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties("receivedMessages")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("sentMessages")
    private Customer customer;

    @NotBlank
    private String content;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
