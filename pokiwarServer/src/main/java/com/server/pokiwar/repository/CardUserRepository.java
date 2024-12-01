package com.server.pokiwar.repository;

import com.server.pokiwar.model.CardUser;
import com.server.pokiwar.model.CountPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardUserRepository extends JpaRepository<CardUser,Long> {

    @Query(value = "SELECT * " +
            "FROM tbl_card_user " +
            "WHERE id_user =:idUser", nativeQuery = true)
    List<CardUser> listCardUser(Long idUser);

}
