package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class PricesCommandHandler implements UserCommandHandler {

    @Value("${telegram.bot.text.prices}")
    private String pricesText;

    private final ButtonUtil buttonUtil;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.PRICES;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(EmojiParser.parseToUnicode(pricesText));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfBackToMainButton());
        return sendMessage;
    }
}
