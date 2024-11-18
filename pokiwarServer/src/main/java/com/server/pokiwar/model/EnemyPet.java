package com.server.pokiwar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_enemy_pet")
public class EnemyPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_pet")
    private Long idPet;

    @Column(name = "id_group_pet")
    private Long idGroupPet;

    @Column(name = "attack")
    private int attack;

    @Column(name = "mana")
    private int mana;

    @Column(name = "blood")
    private int blood;

    @Column(name = "lever")
    private int lever;

    @Column(name = "check_new")
    private boolean checkNew;

    @Column(name = "dame_type_pet")
    private String dameTypePet;
}
