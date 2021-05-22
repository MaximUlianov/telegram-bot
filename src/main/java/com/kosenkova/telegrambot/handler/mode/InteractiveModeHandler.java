package com.kosenkova.telegrambot.handler.mode;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.io.File;
import java.io.Serializable;

public interface InteractiveModeHandler {

    PartialBotApiMethod<? extends Serializable> handleInteractiveMessage(String chatId, String message);

    PartialBotApiMethod<? extends Serializable> handleInteractiveFile(String chatId, File file);
}
