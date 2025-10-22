package com.example.tradetable.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnoreProperties("collection")
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @ManyToMany
    @JoinTable(
        name = "collection_cards",
        joinColumns = @JoinColumn(name = "collection_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private java.util.List<Card> cards = new java.util.ArrayList<>();
}
