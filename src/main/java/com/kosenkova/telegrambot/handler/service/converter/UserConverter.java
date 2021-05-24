package com.kosenkova.telegrambot.handler.service.converter;

import com.kosenkova.telegrambot.model.domain.User;
import com.kosenkova.telegrambot.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User dtoToModel(UserDto userDto);

    UserDto modelToDto(User user);
}
