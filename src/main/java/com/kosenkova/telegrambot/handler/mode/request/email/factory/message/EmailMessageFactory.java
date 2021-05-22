package com.kosenkova.telegrambot.handler.mode.request.email.factory.message;

import com.kosenkova.telegrambot.model.email.EmailData;

import javax.mail.internet.MimeMessage;

public interface EmailMessageFactory {

    MimeMessage build(EmailData emailData);
}
