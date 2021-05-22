package com.kosenkova.telegrambot.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "question", schema = "public")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "text")
    private String text;
}
