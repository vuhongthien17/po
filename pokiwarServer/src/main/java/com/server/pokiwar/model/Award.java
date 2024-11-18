package com.server.pokiwar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_award")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "count")
    private int count;

    @Column(name = "id_group_pet")
    private Long idGroupPet;

    @Column(name = "id_upgrade_stone")
    private Long idUpgradeStone;

    @Column(name = "id_pet")
    private Long idPet;
}
