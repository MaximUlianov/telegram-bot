package com.kosenkova.telegrambot.handler.service.user;

import com.kosenkova.telegrambot.dao.UserDao;
import com.kosenkova.telegrambot.handler.service.converter.UserConverter;
import com.kosenkova.telegrambot.model.domain.User;
import com.kosenkova.telegrambot.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final UserConverter userConverter;

    @Override
    public void saveUser(UserDto userDto) {

        User user = userConverter.dtoToModel(userDto);
        userDao.saveAndFlush(user);
    }
}
