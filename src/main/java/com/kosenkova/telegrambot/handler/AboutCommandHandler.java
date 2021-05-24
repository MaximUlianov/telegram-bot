package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import com.vdurmont.emoji.EmojiParser;
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
public class AboutCommandHandler implements UserCommandHandler {

    private final String aboutText = """
            Студия Борового - лидер белорусского рынка разработки и продвижения корпоративных сайтов и интернет-магазинов.
            Наша цель — сделать оказываемую Студией услугу максимально полезной для бизнеса, технологически и экспертно совершенной, при этом оказывать ее в недостижимые для остального «рынка» сроки.
                        
            Некоторые факты о нашей компании:
                        
                :round_pushpin:Более 10 лет мы успешно работаем на рынке продвижения и разработки сайтов
                :round_pushpin:Мы не раз занимали призовые места в рейтингах Байнета и Рунета
                :round_pushpin:Наши клиенты: ООО «Лифткомплекс», МТЗ, ОАО «Криница», Хайнекен, ООО «Атлантконсалт», УБРиР и многие другие
                :round_pushpin:Основной продукт студии - корпоративные сайты
                :round_pushpin:Студия Борового специализируется на срочной разработке корпоративных сайтов. 2 месяца — это хороший срок для разработки и запуска «несложного» корпоративного проекта
                :round_pushpin:Студия Борового 15 лет занимается поисковым маркетингом, мы понимаем, как правильно осуществлять миграцию сайтов с одной версии на другую
                :round_pushpin:Мы имеем опыт более 200 проектов разработанных на платформе 1С-Битрикс
            """;

    @Value("${telegram.bot.link.about-page}")
    private String aboutPageLink;

    private final ButtonUtil buttonUtil;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.ABOUT;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(EmojiParser.parseToUnicode(aboutText));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfLinkAndBackButton("Ссылка", aboutPageLink));
        return sendMessage;
    }
}
