package com.server.pokiwar.dto;

import com.server.pokiwar.model.Pet;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwardDto {

    private Long id;

    private int count;

    private Long idGroupPet;

    private Long idUpgradeStone;

    private Long idPet;

    private UpgradeStoneDto upgradeStone;

    private PetDto pet;
}
