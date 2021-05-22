package com.kosenkova.telegrambot.handler.service.question;

import com.kosenkova.telegrambot.dao.QuestionDao;
import com.kosenkova.telegrambot.handler.service.converter.QuestionConverter;
import com.kosenkova.telegrambot.model.domain.Question;
import com.kosenkova.telegrambot.model.dto.QuestionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    private final QuestionConverter questionConverter;

    @Override
    public void postQuestion(QuestionDto questionDto) {

        Question question = questionConverter.dtoToModel(questionDto);

        questionDao.save(question);
    }
}
