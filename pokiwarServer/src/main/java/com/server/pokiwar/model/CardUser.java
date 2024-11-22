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
@Table(name = "tbl_card_user")
public class CardUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_card")
    private Long idCard;

    @Column(name = "lever")
    private int lever;

    @Column(name = "value")
    private int value;

    @Column(name = "count")
    private int count;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "create_by")
    private String createBy;


}
