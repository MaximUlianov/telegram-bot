package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartCommandHandler implements UserCommandHandler {

    private final String startText = """
            Здравствуйте.
            Вас приветствует Телеграмм-бот от компании “Студия Борового”.
            Здесь вы можете найти всю интересующую Вас информацию.
            """;

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
