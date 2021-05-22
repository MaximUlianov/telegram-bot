package com.kosenkova.telegrambot.model.dto;

import lombok.Data;

@Data
public class AnswerDto {

    private String id;

    private String text;

    private Boolean sent;

    private QuestionDto question;
}
