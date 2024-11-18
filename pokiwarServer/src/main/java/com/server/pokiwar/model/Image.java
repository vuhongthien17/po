package com.server.pokiwar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ul")
    private String url;

    @Column(name = "id_pet")
    private Long idPet;

    @Column(name = "id_type_pet")
    private Long idTypePet;

    @Column(name = "id_upgrade_stone")
    private Long idUpgradeStone;

    @Column(name = "id_card")
    private Long idCard;
}
