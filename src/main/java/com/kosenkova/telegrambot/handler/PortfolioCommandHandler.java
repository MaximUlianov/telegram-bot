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
public class PortfolioCommandHandler implements UserCommandHandler {

    private final String portfolioText = """
            В нашем портфолио вы можете увидеть примеры проектов, которые мы реализовали. 
            У нас есть следующие разделы:
            
                :red_circle:Корпоративные сайты
                :red_circle:Интернет-магазины
                :red_circle:Порталы
                :red_circle:Сайты банков
                :red_circle:Сайты гос.органов
                :red_circle:Лэндинги
                
            Кроме этого, вы можете выбрать интересующую вас категорию и тематику сайта.
            У нас в портфолио представлены следующие тематики:
            
                :red_circle:ТОП-20
                :red_circle:В2В-дистрибуция
                :red_circle:В2В-услуги
                :red_circle:В2С
                :red_circle:Оборудование
                :red_circle:Продукты питания, FMCG
                :red_circle:Промышленность
                :red_circle:Обучение
                :red_circle:Логистика
            """;

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
        sendMessage.setText(EmojiParser.parseToUnicode(portfolioText));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfLinkAndBackButton("Ссылка", portfolioPageLink));
        return sendMessage;
    }
}
