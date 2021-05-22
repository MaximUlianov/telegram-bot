package com.kosenkova.telegrambot.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "telegram.bot")
@Data
@Slf4j
public class BotProperties {

    private Optional<String> token;

    private Optional<String> username;
}
