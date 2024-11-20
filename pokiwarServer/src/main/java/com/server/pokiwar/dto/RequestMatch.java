package com.server.pokiwar.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestMatch {

    private Long idUser;

    private Long idEnemyPet;

    private Long idPetUser;

    private List<Long> listCardUserId;
}
