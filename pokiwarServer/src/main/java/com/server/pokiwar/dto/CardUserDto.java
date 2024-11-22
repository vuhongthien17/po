package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CardUserDto {

    private Long id;

    private Long idUser;

    private Long idCard;

    private int lever;

    private int count;
    private int value;

    private LocalDateTime createAt;

    private String name;

    private List<ImageDto> imageCard;
}
