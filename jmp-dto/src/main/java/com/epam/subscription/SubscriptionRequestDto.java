package com.epam.subscription;

import com.epam.user.User;

import java.time.LocalDate;

/**
 * @author Dominik_Janiga
 */
class SubscriptionRequestDto {

    private Long id;
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    Subscription toSubscription(User user) {
        return new Subscription(this.id, user, LocalDate.now());
    }
}
