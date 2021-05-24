package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class ContactsCommandHandler implements UserCommandHandler {

    private final String contactsText = """
            Мы находимся по адресу:
            :office: г. Минск, ул. Шафарнянская, 11 (БЦ «Порт»), офис 325 (I), 220125.
                        
            По поводу разработки сайта с нами можно связаться по номеру:
            :phone: +375 (29) 694-22-80 Velcom 
            или электронной почте:
            :e-mail: info@db.by.
                        
            По поводу продвижения сайта с нами можно связаться по номеру:
            :phone: +375 (29) 631-85-98 Velcom 
            или по электронной почте:
            :e-mail: seo@db.by.        
            """;

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
