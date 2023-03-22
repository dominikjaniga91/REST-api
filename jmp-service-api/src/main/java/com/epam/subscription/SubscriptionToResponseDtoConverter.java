package com.epam.subscription;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
class SubscriptionToResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(Subscription from) {
        return from.toResponseDto();
    }
}