package com.kosenkova.telegrambot.handler.mode.request.email;

import com.kosenkova.telegrambot.model.dto.UserDto;

import java.io.File;

public interface EmailSender {

    void sendEmail(UserDto userDto, File file);
}
