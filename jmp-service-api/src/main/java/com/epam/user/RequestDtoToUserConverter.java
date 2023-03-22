package com.epam.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
class RequestDtoToUserConverter implements Converter<UserRequestDto, User> {

    @Override
    public User convert(UserRequestDto from) {
        return from.toUser();
    }
}