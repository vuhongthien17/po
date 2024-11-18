package com.server.pokiwar.repository;

import com.server.pokiwar.model.UpgradeStone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpgradeStoneRepository extends JpaRepository<UpgradeStone, Long> {
}
