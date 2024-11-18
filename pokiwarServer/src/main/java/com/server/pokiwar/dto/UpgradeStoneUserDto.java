package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpgradeStoneUserDto {

    private Long id;

    private Long idAward;

    private Long idUser;

    private UpgradeStoneDto upgradeStone;

    private int count;

    private LocalDateTime createAt;

    private String createBy;

    private LocalDateTime UpdateAt;

    private String UpdateBy;
}
