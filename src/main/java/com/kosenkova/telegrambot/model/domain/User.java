package com.kosenkova.telegrambot.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "email")
    private String email;
}
