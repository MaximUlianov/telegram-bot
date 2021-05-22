package com.kosenkova.telegrambot.handler.service.question;

import com.kosenkova.telegrambot.model.dto.QuestionDto;

public interface QuestionService {

    void postQuestion(QuestionDto questionDto);
}
