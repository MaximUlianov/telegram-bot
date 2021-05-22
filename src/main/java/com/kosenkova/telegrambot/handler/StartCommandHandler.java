package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartCommandHandler implements UserCommandHandler {

    @Value("${telegram.bot.text.start}")
    private String startText;

    private final ButtonUtil buttonUtil;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.START;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(startText);
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(buttonUtil.getMainMenuKeyboard());
        return sendMessage;
    }
}
