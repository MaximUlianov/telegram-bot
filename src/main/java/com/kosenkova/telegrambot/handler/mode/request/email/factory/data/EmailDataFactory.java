package com.kosenkova.telegrambot.handler.mode.request.email.factory.data;

import com.kosenkova.telegrambot.model.dto.UserDto;
import com.kosenkova.telegrambot.model.email.EmailData;

import java.io.File;

public interface EmailDataFactory {

    EmailData build(UserDto userDto, File file);
}
