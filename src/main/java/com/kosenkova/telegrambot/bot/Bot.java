package com.kosenkova.telegrambot.bot;

import com.kosenkova.telegrambot.config.BotProperties;
import com.kosenkova.telegrambot.handler.UserCommandHandler;
import com.kosenkova.telegrambot.handler.mode.InteractiveModeHandler;
import com.kosenkova.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final BotProperties botProperties;

    private final Map<UserCommand, UserCommandHandler> commandHandlers;

    private final List<InteractiveModeHandler> interactiveModeHandlers;

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

            String messageText = null;
            String chatId = null;
            if (message != null && (message.hasText() || message.hasDocument())) {
                messageText = message.getText();
                chatId = message.getChatId().toString();
            } else if (callbackQuery != null && callbackQuery.getData() != null) {
                messageText = callbackQuery.getData();
                chatId = callbackQuery.getMessage().getChatId().toString();
            }

            if (messageText != null && commandHandlers.containsKey(UserCommand.getByValue(messageText))) {
                methods.add(commandHandlers.get(UserCommand.getByValue(messageText)).handleCommandMessage(chatId));
            } else if (message != null && message.hasDocument()) {
                File file = downloadFileByFileId(message.getDocument().getFileId());
                for (InteractiveModeHandler modeHandler : interactiveModeHandlers) {
                    methods.add(modeHandler.handleInteractiveFile(chatId, file));
                }
                file.delete();
            } else {
                for (InteractiveModeHandler modeHandler : interactiveModeHandlers) {
                    methods.add(modeHandler.handleInteractiveMessage(chatId, messageText));
                }
            }

            methods.stream()
                    .filter(Objects::nonNull)
                    .forEach(it -> executeMethod((SendMessage) it));
        }
    }


    @SneakyThrows
    private File downloadFileByFileId(String fileId) {

        GetFile getFile = new GetFile(fileId);
        String filePath = execute(getFile).getFilePath();

        File tempFile = new File("temp." + getFileExtension(filePath));
        return downloadFile(filePath, tempFile);
    }

    @SneakyThrows
    private void executeMethod(SendMessage sendMessage) {
        execute(sendMessage);
    }

    private String getFileExtension(String filePath) {

        return FilenameUtils.getExtension(filePath);
    }
}
