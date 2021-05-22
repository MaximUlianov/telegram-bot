package com.kosenkova.telegrambot.handler.service.answer;

import com.kosenkova.telegrambot.model.dto.AnswerDto;

import java.util.List;

public interface AnswerService {

    List<AnswerDto> getNonSentAnswers();

    void updateAnswerStatusToSent(String answerId);
}
