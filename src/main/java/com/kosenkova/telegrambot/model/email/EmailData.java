package com.kosenkova.telegrambot.model.email;

import lombok.Data;

import java.io.File;

@Data
public class EmailData {

    private String name;

    private String number;

    private String email;

    private File file;
}
