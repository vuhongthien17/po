package com.server.pokiwar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAward {
    private String code;
    private Long idGroupPet;
    private Long idPet;
    private Long idUser;
}
