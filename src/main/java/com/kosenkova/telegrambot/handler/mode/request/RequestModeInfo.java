package com.kosenkova.telegrambot.handler.mode.request;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestModeInfo {

    private Map<String, RequestModeStatus> requestModeInfo;

    public boolean activateRequestMode(String chatId) {

        if (requestModeInfo == null) {
            requestModeInfo = new HashMap<>();
        }

        requestModeInfo.put(chatId, RequestModeStatus.NAME_PROVIDE);

        return true;
    }

    public boolean provideNumber(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        requestModeInfo.put(chatId, RequestModeStatus.NUMBER_PROVIDE);

        return true;
    }

    public boolean provideEmail(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        requestModeInfo.put(chatId, RequestModeStatus.EMAIL_PROVIDE);

        return true;
    }

    public boolean provideFile(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        requestModeInfo.put(chatId, RequestModeStatus.FILE_PROVIDE);

        return true;
    }

    public boolean deactivateMode(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        requestModeInfo.remove(chatId);

        return true;
    }

    public boolean isRequestModeActive(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        return requestModeInfo.containsKey(chatId);
    }

    public boolean isProvideName(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        return RequestModeStatus.NAME_PROVIDE.equals(requestModeInfo.get(chatId));
    }

    public boolean isProvideNumber(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        return RequestModeStatus.NUMBER_PROVIDE.equals(requestModeInfo.get(chatId));
    }

    public boolean isProvideEmail(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        return RequestModeStatus.EMAIL_PROVIDE.equals(requestModeInfo.get(chatId));
    }

    public boolean isProvideFile(String chatId) {

        if (requestModeInfo == null) {
            return false;
        }

        return RequestModeStatus.FILE_PROVIDE.equals(requestModeInfo.get(chatId));
    }
}
