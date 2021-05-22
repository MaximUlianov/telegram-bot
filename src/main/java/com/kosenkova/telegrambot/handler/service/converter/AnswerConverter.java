package com.kosenkova.telegrambot.handler.service.converter;

import com.kosenkova.telegrambot.model.domain.Answer;
import com.kosenkova.telegrambot.model.dto.AnswerDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface AnswerConverter {

    Answer dtoToModel(AnswerDto answerDto);

    AnswerDto modelToDto(Answer answer);
}
