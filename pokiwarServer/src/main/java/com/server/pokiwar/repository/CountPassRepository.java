package com.server.pokiwar.repository;

import com.server.pokiwar.model.CountPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountPassRepository extends JpaRepository<CountPass ,Long> {

    @Query(value = "SELECT * " +
            "FROM tbl_count_pass " +
            "WHERE id_pet =:idEnemyPet AND id_user =:idUser", nativeQuery = true)
    Optional<CountPass> countPassBy(Long idEnemyPet, Long idUser);

}
