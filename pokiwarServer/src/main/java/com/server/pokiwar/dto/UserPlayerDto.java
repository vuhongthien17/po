package com.server.pokiwar.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class UserPlayerDto {

    private Long id;
    private String name;
    private String password;
    private String user;
    private int energy;
    private int lever;
    private int attack;
    private int mana;
    private int blood;
    private Long idPetUser;
    private String namePet;
    private List<ImageDto> imagePet;
    private List<ImageDto> imageUser;
    private List<CardUserDto> listCard;


}
