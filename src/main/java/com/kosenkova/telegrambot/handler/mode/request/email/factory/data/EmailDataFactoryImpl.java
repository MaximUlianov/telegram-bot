package com.kosenkova.telegrambot.handler.mode.request.email.factory.data;

import com.kosenkova.telegrambot.model.dto.UserDto;
import com.kosenkova.telegrambot.model.email.EmailData;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailDataFactoryImpl implements EmailDataFactory {

    @Override
    public EmailData build(UserDto userDto, File file) {

        EmailData emailData = new EmailData();

        emailData.setName(userDto.getName());
        emailData.setNumber(userDto.getNumber());
        emailData.setEmail(userDto.getEmail());
        emailData.setFile(file);

        return emailData;
    }
}
