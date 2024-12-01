package com.server.pokiwar.repository;

import com.server.pokiwar.model.CardUser;
import com.server.pokiwar.model.PetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetUserRepository extends JpaRepository<PetUser,Long> {

    @Query(value = "SELECT * " +
            "FROM tbl_pet_user " +
            "WHERE id_user =:idUser", nativeQuery = true)
    List<PetUser> listPetUser(Long idUser);
}
