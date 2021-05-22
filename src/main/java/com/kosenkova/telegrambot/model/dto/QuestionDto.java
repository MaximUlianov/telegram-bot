package com.kosenkova.telegrambot.model.dto;

import lombok.Data;

@Data
public class QuestionDto {

    private String id;

    private String chatId;

    private String text;
}
