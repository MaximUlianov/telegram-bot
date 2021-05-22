package com.kosenkova.telegrambot.handler;

import com.kosenkova.telegrambot.model.UserCommand;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.io.Serializable;

public interface UserCommandHandler {

    UserCommand getHandlerCommand();

    PartialBotApiMethod<? extends Serializable> handleCommandMessage(String chatId);
}
