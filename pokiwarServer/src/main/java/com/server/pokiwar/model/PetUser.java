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
@Table(name = "tbl_pet_user")
public class PetUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_award")
    private Long idAward;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_pet")
    private Long idPet;

    @Column(name = "attack")
    private int attack;

    @Column(name = "dame_type_pet")
    private String dameTypePet;

    @Column(name = "mana")
    private int mana;

    @Column(name = "blood")
    private int blood;

    @Column(name = "lever")
    private int lever;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_at")
    private LocalDateTime UpdateAt;

    @Column(name = "update_by")
    private String UpdateBy;

    @Column(name = "check_new")
     private boolean checkNew;

    @Column(name = "thumbnail_pet")
    private String thumbnailPet;
}
