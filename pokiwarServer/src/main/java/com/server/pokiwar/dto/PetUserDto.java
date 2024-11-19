package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PetUserDto {

    private Long id;

    private Long idAward;

    private Long idUser;

    private Long idPet;

    private String namePet;

    private int attack;

    private int mana;

    private int blood;

    private int lever;

    private LocalDateTime createAt;

    private String createBy;

    private boolean checkNew;

    private List<ImageDto> image;
}
