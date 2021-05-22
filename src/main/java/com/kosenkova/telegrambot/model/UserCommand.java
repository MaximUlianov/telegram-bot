package com.kosenkova.telegrambot.model;

public enum UserCommand {

    START("/start"),

    HELP("/help"),

    SETTINGS("/settings"),

    ABOUT("/about"),

    SERVICES("/services"),

    BENEFITS("/benefits"),

    PORTFOLIO("/portfolio"),

    FAQ("/faq"),

    PRICES("/prices"),

    FEEDBACKS("/feedbacks"),

    CONTACTS("/contacts"),

    REQUEST("/request"),

    QUESTION("/question"),

    BACK_TO_MAIN_MENU("/back_to_main_menu");

    private final String value;

    UserCommand(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static UserCommand getByValue(String value) {

        for (UserCommand command : values()) {
            if (value.equals(command.getValue())) {
                return command;
            }
        }
        return null;
    }
}
