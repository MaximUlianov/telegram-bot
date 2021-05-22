package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.mode.request.RequestModeInfo;
import com.kosenkova.telegrambot.model.UserCommand;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestCommandHandler implements UserCommandHandler {

    private final RequestModeInfo requestModeInfo;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.REQUEST;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        requestModeInfo.activateRequestMode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(EmojiParser.parseToUnicode("Пожалуйста, введите ваше имя"));

        return sendMessage;
    }
}
