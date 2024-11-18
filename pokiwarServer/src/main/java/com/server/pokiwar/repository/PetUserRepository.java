package com.server.pokiwar.repository;

import com.server.pokiwar.model.PetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetUserRepository extends JpaRepository<PetUser,Long> {
}
