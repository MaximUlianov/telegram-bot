package com.kosenkova.telegrambot.handler.mode.question;

import com.kosenkova.telegrambot.handler.mode.InteractiveModeHandler;
import com.kosenkova.telegrambot.handler.service.question.QuestionService;
import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.dto.QuestionDto;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.File;
import java.io.Serializable;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestionModeHandler implements InteractiveModeHandler {

    private final QuestionService questionService;

    private final QuestionModeInfo questionModeInfo;

    private final ButtonUtil buttonUtil;

    @Override
    public PartialBotApiMethod<? extends Serializable> handleInteractiveMessage(String chatId, String message) {

        if (questionModeInfo.isQuestionModeActive(chatId)) {

            QuestionDto dto = new QuestionDto();
            dto.setChatId(chatId);
            dto.setText(message);

            questionService.postQuestion(dto);
            questionModeInfo.deactivateMode(chatId);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(EmojiParser.parseToUnicode("Ваш вопрос был записан"));
            sendMessage.enableMarkdown(true);
            sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfBackToMainButton());

            return sendMessage;
        }

        return null;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleInteractiveFile(String chatId, File file) {
        return null;
    }
}
