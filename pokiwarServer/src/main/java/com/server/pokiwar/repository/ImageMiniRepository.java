package com.server.pokiwar.repository;

import com.server.pokiwar.model.ImageMini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMiniRepository extends JpaRepository<ImageMini,Long> {
}
