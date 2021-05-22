package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.mode.question.QuestionModeInfo;
import com.kosenkova.telegrambot.model.UserCommand;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionCommandHandler implements UserCommandHandler {

    private final QuestionModeInfo questionModeInfo;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.QUESTION;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        questionModeInfo.activateQuestionMode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(EmojiParser.parseToUnicode("Пожалуйста, введите ваш вопрос"));

        return sendMessage;
    }
}
