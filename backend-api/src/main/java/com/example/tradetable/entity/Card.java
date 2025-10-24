//Need to be able to map multiple providers to a single card and multiple cards to a single provider for the collection list
//Many-to-Many relationship between Card and Provider entities not working properly
package com.example.tradetable.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "collections",
        joinColumns = @JoinColumn(name = "card_id"),
        inverseJoinColumns = @JoinColumn(name = "provider_id")
    )
    @JsonIgnoreProperties("collection")
    private List<Provider> providers = new ArrayList<>();

    @NotBlank
    private String name;

    @NotBlank
    private String deck;

    @NotBlank
    private String game;

    @NotBlank
    private String rarity;
}
