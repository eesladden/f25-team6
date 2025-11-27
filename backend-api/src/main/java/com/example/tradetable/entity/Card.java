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

    @JsonIgnoreProperties({"collection", "providers"})
    private List<Provider> providers = new ArrayList<>();

    private String imagePath;

    @NotBlank
    private String name;

    @NotBlank
    private String set;

    @NotBlank
    private String game;

    @NotBlank
    private String rarity;
}
