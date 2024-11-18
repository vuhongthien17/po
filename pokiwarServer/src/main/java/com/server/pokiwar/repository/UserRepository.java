package com.server.pokiwar.repository;

import com.server.pokiwar.model.UserPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPlayer,Long> {
    Optional<UserPlayer> findByIdAndCode(Long idUser, String code);
}
