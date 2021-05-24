package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.UserCommand;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class FAQCommandHandler implements UserCommandHandler {

    private final String faqText = """
            Мы собрали для вас ответы на наиболее часто задаваемые вопросы от наших клиентов по поводу наших услуг:
                        
                :question:Сколько времени займет продвижение сайта в ТОП-10?
                
                Продвижение не быстрый процесс. Первые положительные результаты, как правило, можно увидеть через несколько месяцев после начала работ по продвижению. Попадание в ТОП-10, ТОП-5, ТОП-3, ТОП-1 будет зависеть от наличия сильных конкурентов в данной нише, начального состояния сайта, стартовых позиций и др.      
                
                :question:Сколько обычно разрабатывается сайт?
                        
                Студия Борового специализируется на срочной разработке корпоративных сайтов. 2 месяца — это хороший срок для разработки и запуска «несложного» корпоративного проекта. Мы запускали проекты и за 7 дней, за 2 недели и за 30 дней. Практика показывает, что разработка сайта за 1 месяц возможна при ограниченном объеме работ (проектировании и разработке 3-4 ключевых макетов страниц).
                        
                :question:От чего зависит цена разработки сайта?
                        
                На цену сайта влияет множество факторов, начиная от сложности функционала и заканчивая дизайном. Например, простой сайт визитка обойдется явно дешевле, чем сайт интернет-магазина со множеством товаров. Именно поэтому назвать точную цену готового программного продукта не представляется возможным.
                        
                :question:Можно ли заказать разработку сайта сразу вместе с продвижением?
                        
                Да, можно. Более того, именно комплекс данных услуг позволяет добиться наиболее высоких результатов.
                        
                :question:Есть ли у вас услуга продвижения в социальных сетях?
                        
                Нет, компания “Студия Борового” занимается исключительно разработкой сайтов и продвижением в поисковых системах.
                        
                :question:Есть ли у вас услуга аудита сайта?
                        
                Да, мы проводим комплексный аудит сайта, предоставляем вам наиболее полную информацию о техническом состоянии сайта, успешности его продвижения и наши рекомендации по улучшению.    
            """;

    private final ButtonUtil buttonUtil;

    @Override
    public UserCommand getHandlerCommand() {
        return UserCommand.FAQ;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(EmojiParser.parseToUnicode(faqText));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfBackToMainButton());
        return sendMessage;
    }
}
