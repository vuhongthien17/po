package com.server.pokiwar.repository;

import com.server.pokiwar.model.EnemyPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyPetRepository extends JpaRepository<EnemyPet,Long> {

    @Query(value = "SELECT * " +
            "FROM tbl_enemy_pet " +
            "WHERE id_group_pet =:idGroupPet", nativeQuery = true)
    List<EnemyPet> getAllPetByGroup (Long idGroupPet);
}
