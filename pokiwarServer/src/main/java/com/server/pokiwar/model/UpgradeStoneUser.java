package com.server.pokiwar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_upgrade_stone_use")
public class UpgradeStoneUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_award")
    private Long idAward;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_upgrade_stone")
    private Long idUpgradeStone;

    @Column(name = "count")
    private int count;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_at")
    private LocalDateTime UpdateAt;

    @Column(name = "update_by")
    private String UpdateBy;
}
