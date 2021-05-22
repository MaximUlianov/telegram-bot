package com.kosenkova.telegrambot.handler.mode.request;

import com.kosenkova.telegrambot.handler.mode.InteractiveModeHandler;
import com.kosenkova.telegrambot.handler.mode.request.email.EmailSender;
import com.kosenkova.telegrambot.handler.service.user.UserService;
import com.kosenkova.telegrambot.handler.util.ButtonUtil;
import com.kosenkova.telegrambot.model.dto.UserDto;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RequestModeHandler implements InteractiveModeHandler {

    private Map<String, UserDto> userInfo;

    private final RequestModeInfo requestModeInfo;

    private final UserService userService;

    private final EmailSender emailSender;

    private final ButtonUtil buttonUtil;

    @Override
    public PartialBotApiMethod<? extends Serializable> handleInteractiveMessage(String chatId, String message) {

        if (requestModeInfo.isRequestModeActive(chatId)) {

            if (userInfo == null) {
                userInfo = new HashMap<>();
            }

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            if (requestModeInfo.isProvideName(chatId)) {

                sendMessage.setText(EmojiParser.parseToUnicode("Пожалуйста, введите ваш номер телефона"));
                handleNameProvisioning(chatId, message);

            } else if (requestModeInfo.isProvideNumber(chatId)) {

                sendMessage.setText(EmojiParser.parseToUnicode("Пожалуйста, введите ваш адрес электронной почты"));
                handleNumberProvisioning(chatId, message);

            } else if (requestModeInfo.isProvideEmail(chatId)) {

                sendMessage.setText(EmojiParser.parseToUnicode("Пожалуйста, прикрепите файл с заданием (можно не прикреплять, просто нажмите отправить)"));
                handleEmailProvisioning(chatId, message);

            } else if (requestModeInfo.isProvideFile(chatId)) {

                handleFileProvisioning(chatId, null);

                sendMessage.setText(EmojiParser.parseToUnicode("Ваш запрос был отправлен"));
                sendMessage.enableMarkdown(true);
                sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfBackToMainButton());
            }

            return sendMessage;
        }

        return null;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleInteractiveFile(String chatId, File file) {

        if (requestModeInfo.isRequestModeActive(chatId)) {

            if (userInfo == null) {
                userInfo = new HashMap<>();
            }

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            if (requestModeInfo.isProvideFile(chatId)) {

                handleFileProvisioning(chatId, file);

                sendMessage.setText(EmojiParser.parseToUnicode("Ваш запрос был отправлен"));
                sendMessage.enableMarkdown(true);
                sendMessage.setReplyMarkup(buttonUtil.getKeyboardOfBackToMainButton());
            }

            return sendMessage;
        }

        return null;
    }

    private void handleNameProvisioning(String chatId, String message) {

        UserDto userDto = new UserDto();
        userDto.setName(message);
        userInfo.put(chatId, userDto);
        requestModeInfo.provideNumber(chatId);
    }

    private void handleNumberProvisioning(String chatId, String message) {

        UserDto userDto = userInfo.get(chatId);
        userDto.setNumber(message);
        userInfo.put(chatId, userDto);
        requestModeInfo.provideEmail(chatId);
    }

    private void handleEmailProvisioning(String chatId, String message) {

        UserDto userDto = userInfo.get(chatId);
        userDto.setEmail(message);
        userInfo.put(chatId, userDto);
        requestModeInfo.provideFile(chatId);
    }

    private void handleFileProvisioning(String chatId, File file) {

        UserDto userDto = userInfo.get(chatId);
        userService.saveUser(userDto);
        emailSender.sendEmail(userDto, file);
        userInfo.remove(chatId);
        requestModeInfo.deactivateMode(chatId);
    }
}
