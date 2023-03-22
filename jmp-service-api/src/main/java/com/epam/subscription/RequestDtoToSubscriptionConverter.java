package com.epam.subscription;

import com.epam.user.User;
import com.epam.user.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;


@Service
class RequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    private final UserRepository userRepository;

    RequestDtoToSubscriptionConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Subscription convert(SubscriptionRequestDto from) {
        Long userId = from.getUserId();
        User foundUser = this.userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        return from.toSubscription(foundUser);
    }
}