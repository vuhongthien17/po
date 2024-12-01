package com.server.pokiwar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_image_mini")
public class ImageMini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ul")
    private String url;

    @Column(name = "id_pet")
    private Long idPet;

    @Column(name = "id_group_pet")
    private Long idGroupPet;

    @Column(name = "id_user")
    private Long idUser;
}
