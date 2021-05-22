package com.kosenkova.telegrambot.handler.service.answer;

import com.kosenkova.telegrambot.dao.AnswerDao;
import com.kosenkova.telegrambot.handler.service.converter.AnswerConverter;
import com.kosenkova.telegrambot.model.domain.Answer;
import com.kosenkova.telegrambot.model.dto.AnswerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerDao answerDao;

    private final AnswerConverter answerConverter;

    @Override
    public List<AnswerDto> getNonSentAnswers() {

        return answerDao.getAllBySentFalse()
                .orElseGet(List::of)
                .stream()
                .map(answerConverter::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAnswerStatusToSent(String answerId) {

        Answer answer = answerDao.getById(Long.parseLong(answerId)).orElseThrow();
        answer.setSent(Boolean.TRUE);

        answerDao.saveAndFlush(answer);
    }
}
