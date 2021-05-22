package com.kosenkova.telegrambot.scheduler;

public interface ScheduledSender {

    void send();

    void chekNonSentAnswersAndSend();
}
