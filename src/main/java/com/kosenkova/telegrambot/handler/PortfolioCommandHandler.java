package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class PortfolioCommandHandler implements UserCommandHandler {

    @Value("${telegram.bot.text.portfolio}")
    private String portfolioText;

    @Value("${telegram.bot.link.portfolio-page}")
    private String portfolioPageLink;

    private final ButtonUtil buttonUtil;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.PORTFOLIO;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(portfolioText);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfLinkAndBackButton("Ссылка", portfolioPageLink));
        return sendMessage;
    }
}
