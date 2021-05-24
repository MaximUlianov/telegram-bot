package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class BackToMainMenuCommandHandler implements UserCommandHandler {

    private final String mainMenuText = "Главное меню:";

    private final ButtonUtil buttonUtil;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.BACK_TO_MAIN_MENU;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(mainMenuText);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getMainMenuKeyboard());
        return sendMessage;
    }
}
