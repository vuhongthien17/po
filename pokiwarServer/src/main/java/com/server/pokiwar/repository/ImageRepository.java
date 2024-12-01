package com.server.pokiwar.repository;

import com.server.pokiwar.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "SELECT * " +
            "FROM tbl_image " +
            "WHERE id_upgrade_stone =:idUpgradeStone", nativeQuery = true)
    List<Image> findByIdUpgradeStone(Long idUpgradeStone);

    @Query(value = "SELECT * " +
            "FROM tbl_image " +
            "WHERE id_pet =:idPet", nativeQuery = true)
    List<Image> findByIdPet(Long idPet);

    @Query(value = "SELECT * " +
            "FROM tbl_image " +
            "WHERE id_card =:idCard", nativeQuery = true)
    List<Image> findByIdCard(Long idCard);

    @Query(value = "SELECT * " +
            "FROM tbl_image " +
            "WHERE id_user =:idUser", nativeQuery = true)
    List<Image> findByIdUser(Long idUser);

    @Query(value = "SELECT * " +
            "FROM tbl_image " +
            "WHERE id_type_pet =:idTypePet", nativeQuery = true)
    List<Image> findByIdTypePet(Long idTypePet);
}
