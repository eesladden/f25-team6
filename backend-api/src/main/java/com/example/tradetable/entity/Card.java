package com.example.tradetable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@Table(name = "cards")
//Card should include an auto-generated id number, a name, deck, game, rarity.
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String deck;

    @NotBlank
    private String game;

    @NotBlank
    private String rarity;
}
