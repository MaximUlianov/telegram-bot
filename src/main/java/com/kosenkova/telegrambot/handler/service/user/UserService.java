package com.kosenkova.telegrambot.handler.service.user;

import com.kosenkova.telegrambot.model.dto.UserDto;

public interface UserService {

    void saveUser(UserDto userDto);
}
