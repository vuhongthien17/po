package com.server.pokiwar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "power")
    private int power;

    @Column(name = "mana")
    private int mana;

    @Column(name = "blood")
    private int blood;

    @Column(name = "attack")
    private int attack;

    @Column(name = "maxLever")
    private int maxLever;

    @Column(name = "lever")
    private int lever;
}
