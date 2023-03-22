package com.epam.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
class UserToResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User from) {
        return from.toResponseDto();
    }
}