package com.kosenkova.telegrambot.scheduler;

import com.kosenkova.telegrambot.bot.Bot;
import com.kosenkova.telegrambot.handler.service.answer.AnswerService;
import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.dto.AnswerDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledMessagesSender implements ScheduledSender {

    private static final String ANSWER_PATTERN = "Вопрос: %s \nОтвет: %s";

    private final Bot bot;

    private final ButtonUtil buttonUtil;

    private final AnswerService answerService;

    @Override
    @Scheduled(fixedDelayString = "${schedule.fixedDelayMs}")
    public void chekNonSentAnswersAndSend() {

        answerService.getNonSentAnswers().forEach(this::sendAnswer);
    }

    @Override
    public void send() {

    }

    private void sendAnswer(AnswerDto answerDto) {

        SendMessage sendMessage = new SendMessage();

        String messageText = String.format(ANSWER_PATTERN, answerDto.getQuestion().getText(), answerDto.getText());

        sendMessage.setChatId(answerDto.getQuestion().getChatId());
        sendMessage.setText(messageText);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfBackToMainButton());

        executeWithExceptionsCheck(sendMessage);

        answerService.updateAnswerStatusToSent(answerDto.getId());
    }

    @SneakyThrows
    private void executeWithExceptionsCheck(SendMessage sendMessage) {
        bot.execute(sendMessage);
    }
}
