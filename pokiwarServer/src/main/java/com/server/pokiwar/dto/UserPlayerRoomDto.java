package com.server.pokiwar.dto;

import com.server.pokiwar.model.EnemyPet;
import lombok.Data;

import java.util.List;

@Data
public class UserPlayerRoomDto {

    private Long id;
    private String name;
    private String user;
    private int gold;
    private int money;
    private int energy;
    private int energyFull;
    private int lever;
    private int countPass;
    private Long idPetUser;
    private String thumbnailPetUser;
    private List<PetUserDto> ListChoosePet;
    private List<ImageDto> imageUser;
    private List<CardUserDto> ListChooseCard;
    //enemy
    private String namePetEnemy;
    private List<ImageDto> imageEnemyPet;
    private EnemyPet enemyPet;


}
