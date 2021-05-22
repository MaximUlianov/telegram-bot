package com.kosenkova.telegrambot.handler.config;

import com.kosenkova.telegrambot.handler.UserCommandHandler;
import com.kosenkova.telegrambot.model.UserCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class HandlersConfiguration {

    @Bean
    public Map<UserCommand, UserCommandHandler> commandHandlers(List<UserCommandHandler> userMessageHandlers) {

        return userMessageHandlers.stream().collect(Collectors.toMap(UserCommandHandler::getHandlerCommand, Function.identity()));
    }

}
