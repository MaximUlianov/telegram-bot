package com.kosenkova.telegrambot.handler.service.converter;

import com.kosenkova.telegrambot.model.domain.Question;
import com.kosenkova.telegrambot.model.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface QuestionConverter {

    Question dtoToModel(QuestionDto questionDto);

    QuestionDto modelToDto(Question question);
}
