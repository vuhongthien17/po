package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnemyPetDTO {

    private Long id;

    private Long idPet;

    private Long idGroupPet;

    private int lever;

    private int requestPass;

    private int count;

    private String thumbnail;
}
