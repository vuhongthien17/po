package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class CardDto {

    private Long id;

    private String name;

    private String description;

    private int power;

    private int mana;

    private int blood;

    private int attack;

    private int maxLever;

    private int lever;

    private List<ImageDto> image;
}
