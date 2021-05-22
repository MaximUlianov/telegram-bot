package com.kosenkova.telegrambot.handler.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosenkova.telegrambot.model.MenuButtonModel;
import com.kosenkova.telegrambot.model.UserCommand;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ButtonUtil {

    @Value("classpath:main-menu-buttons.json")
    private Resource mainMenuButtonsResource;

    private final ObjectMapper objectMapper;

    public InlineKeyboardButton createButton(String text, String callbackData, String url) {

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(text));
        button.setCallbackData(callbackData);
        button.setUrl(url);
        return button;
    }

    public InlineKeyboardButton getBackToMenuButton() {

        return createButton(":point_left: В главное меню", UserCommand.BACK_TO_MAIN_MENU.getValue(), null);
    }

    @SneakyThrows
    public InlineKeyboardMarkup getMainMenuKeyboard() {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        List<MenuButtonModel> menuButtons = objectMapper.readValue(mainMenuButtonsResource.getFile(), new TypeReference<>() {});

        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (MenuButtonModel buttonModel: menuButtons) {
            row.add(createButton(buttonModel.getName(), buttonModel.getCommand(), null));
            if (row.size() == 2) {
                keyboard.add(row);
                row = new ArrayList<>();
            }
        }
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup getKeyboardOfBackToMainButton() {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(List.of(getBackToMenuButton())));

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup getKeyboardOfLinkAndBackButton(String text, String url) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowOne = List.of(createButton(text, null, url));
        List<InlineKeyboardButton> rowTwo = List.of(getBackToMenuButton());
        keyboardMarkup.setKeyboard(List.of(rowOne, rowTwo));

        return keyboardMarkup;
    }
}
