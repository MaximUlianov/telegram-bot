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
public class BenefitsCommandHandler implements UserCommandHandler {

    private final String benefitsText = """
            Наши преимущества:
            
                :star:Сжатые сроки разработки проекта (2 месяца — это хороший срок для разработки и запуска «несложного» корпоративного проекта)
                :star:Комплексный подход - мы начинаем продвижение сайта еще на этапе его разработки. Таким образом можно получить наиболее высокий результат.
                :star:Налаженные бизнес-процессы и соответствующая экспертиза
                :star:Использование эффективных подходов к разработке и продвижению сайтов
                :star:Экспертность, подтвержденная многочисленными рейтингами и наградами
                :star:Гарантия качества      
            """;

    private final ButtonUtil buttonUtil;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.BENEFITS;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(EmojiParser.parseToUnicode(benefitsText));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfBackToMainButton());
        return sendMessage;
    }
}
