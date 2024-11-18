package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class PetDto {

    private Long id;

    private Long idGroupPet;

    private Long idTypePetRestrain;

    private Long idCard;

    private Long idTypePet;

    private String description;

    private String name;

    private int attack;

    private int mana;

    private int blood;

    private int stepNo;

    private int conditionPass;

    private String checkNew;

    private int lever;

    private String active;

    private List<ImageDto> image;
}
