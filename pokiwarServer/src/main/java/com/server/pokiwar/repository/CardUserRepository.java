package com.server.pokiwar.repository;

import com.server.pokiwar.model.CardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardUserRepository extends JpaRepository<CardUser,Long> {
}
