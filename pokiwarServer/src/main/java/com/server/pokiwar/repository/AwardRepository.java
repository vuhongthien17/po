package com.server.pokiwar.repository;

import com.server.pokiwar.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award,Long> {

    @Query(value = "SELECT * " +
            "FROM tbl_award " +
            "WHERE id_pet =:idPet AND id_group_pet =:idGroupPet", nativeQuery = true)
    List<Award> findALLByCondition(Long idPet, Long idGroupPet);

}
