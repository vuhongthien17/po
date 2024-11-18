package com.server.pokiwar.repository;

import com.server.pokiwar.model.UpgradeStoneUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpgradeStoneUserRepository extends JpaRepository<UpgradeStoneUser,Long> {
}
