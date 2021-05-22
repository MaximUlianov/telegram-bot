package com.kosenkova.telegrambot.handler.mode.question;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionModeInfo {

    private Map<String, QuestionModeStatus> questionModeInfo;


    public boolean activateQuestionMode(String chatId) {

        if (questionModeInfo == null) {
            questionModeInfo = new HashMap<>();
        }

        questionModeInfo.put(chatId, QuestionModeStatus.ACTIVE);

        return true;
    }

    public boolean isQuestionModeActive(String chatId) {

        if (questionModeInfo == null) {
            return false;
        }

        return QuestionModeStatus.ACTIVE.equals(questionModeInfo.get(chatId));
    }

    public boolean deactivateMode(String chatId) {

        if (questionModeInfo == null) {
            return false;
        }

        questionModeInfo.remove(chatId);

        return true;
    }
}
