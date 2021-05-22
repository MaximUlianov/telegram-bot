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
public class ContactsCommandHandler implements UserCommandHandler {

    @Value("${telegram.bot.text.contacts}")
    private String contactsText;

    @Value("${telegram.bot.link.contacts-page}")
    private String contactsPageLink;

    private final ButtonUtil buttonUtil;
    
    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.CONTACTS;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(EmojiParser.parseToUnicode(contactsText));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfLinkAndBackButton("Ссылка", contactsPageLink));
        return sendMessage;
    }
}
