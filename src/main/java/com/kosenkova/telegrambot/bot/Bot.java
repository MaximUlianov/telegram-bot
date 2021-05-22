package com.kosenkova.telegrambot.bot;

import com.kosenkova.telegrambot.config.BotProperties;
import com.kosenkova.telegrambot.handler.UserCommandHandler;
import com.kosenkova.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final BotProperties botProperties;

    private final Map<UserCommand, UserCommandHandler> commandHandlers;

    @Override
    public String getBotToken() {
        return botProperties.getToken().orElseThrow();
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername().orElseThrow();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() || update.hasCallbackQuery()) {

            List<PartialBotApiMethod<? extends Serializable>> methods = new ArrayList<>();

            Message message = update.getMessage();
            CallbackQuery callbackQuery = update.getCallbackQuery();

            String command = null;
            String chatId = null;
            if (message != null && message.hasText()){
                command = message.getText();
                chatId = message.getChatId().toString();
            } else if (callbackQuery != null && callbackQuery.getData() != null) {
               command = callbackQuery.getData();
               chatId = callbackQuery.getMessage().getChatId().toString();
            }

            if (commandHandlers.containsKey(UserCommand.getByValue(command))) {
                methods.add(commandHandlers.get(UserCommand.getByValue(command)).handleCommandMessage(chatId));
            }

            methods.forEach(it -> executeMethod((SendMessage) it));
        }
    }

    @SneakyThrows
    private void executeMethod(SendMessage sendMessage) {
        execute(sendMessage);
    }
}
