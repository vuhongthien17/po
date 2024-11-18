package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class UpgradeStoneDto {

    private Long id;

    private Long idTypePet;

    private String name;

    private String description;

    private int lever;

    private List<ImageDto> image;
}
