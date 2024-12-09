package com.server.pokiwar.repository;

import com.server.pokiwar.model.GroupPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPetRepository extends JpaRepository<GroupPet, Long> {
}
