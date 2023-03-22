package com.epam.subscription;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dominik_Janiga
 */
@Service
class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final ConversionService conversionService;

    SubscriptionServiceImpl(SubscriptionRepository repository,
                            ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = this.conversionService.convert(subscriptionRequestDto, Subscription.class);
        Subscription savedSubscription = this.repository.save(subscription);
        return this.conversionService.convert(savedSubscription, SubscriptionResponseDto.class);
    }

    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = this.conversionService.convert(subscriptionRequestDto, Subscription.class);
        Subscription savedSubscription = this.repository.save(subscription);
        return this.conversionService.convert(savedSubscription, SubscriptionResponseDto.class);
    }

    @Override
    public void deleteSubscription(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long id) {
        Subscription subscription = this.repository.findById(id).orElseThrow(IllegalArgumentException::new);
        return this.conversionService.convert(subscription, SubscriptionResponseDto.class);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscription() {
        List<Subscription> subscriptions = this.repository.findAll();
        return subscriptions.stream()
                .map(s -> this.conversionService.convert(s, SubscriptionResponseDto.class))
                .collect(Collectors.toList());
    }
}
