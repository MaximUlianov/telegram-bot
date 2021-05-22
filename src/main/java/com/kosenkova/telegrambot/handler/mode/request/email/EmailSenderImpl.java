package com.kosenkova.telegrambot.handler.mode.request.email;

import com.kosenkova.telegrambot.handler.mode.request.email.factory.data.EmailDataFactory;
import com.kosenkova.telegrambot.handler.mode.request.email.factory.message.EmailMessageFactory;
import com.kosenkova.telegrambot.model.dto.UserDto;
import com.kosenkova.telegrambot.model.email.EmailData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSender {

    private final EmailDataFactory emailDataFactory;

    private final EmailMessageFactory emailMessageFactory;

    private final JavaMailSender javaMailSender;

    @Override
    @SneakyThrows
    public void sendEmail(UserDto userDto, File file) {

        EmailData emailData = emailDataFactory.build(userDto, file);
        MimeMessage message = emailMessageFactory.build(emailData);
        javaMailSender.send(message);
    }
}
