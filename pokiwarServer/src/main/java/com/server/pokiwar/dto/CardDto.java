package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class CardDto {

    private Long id;

    private String name;

    private String description;

    private int value;

    private int maxLever;

    private int lever;

    private List<ImageDto> image;
}
