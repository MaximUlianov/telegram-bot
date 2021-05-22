package com.kosenkova.telegrambot.scheduler;

import com.kosenkova.telegrambot.bot.Bot;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledMessagesSender implements ScheduledSender{

    private final Bot bot;

    @Override
    //@Scheduled(fixedDelayString = "${schedule.fixedDelayMs}", initialDelay = 50000)
    public void send() {

    }

    @SneakyThrows
    private void executeWithExceptionsCheck(SendMessage sendMessage) {
        bot.execute(sendMessage);
    }


}
