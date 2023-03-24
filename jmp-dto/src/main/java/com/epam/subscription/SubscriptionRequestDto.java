package com.epam.subscription;

import com.epam.user.User;

import java.time.LocalDate;

/**
 * @author Dominik_Janiga
 */
class SubscriptionRequestDto {

    private Long id;
    private Long userId;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    Long getUserId() {
        return userId;
    }

    void setUserId(Long userId) {
        this.userId = userId;
    }

    Subscription toSubscription(User user) {
        return new Subscription(this.id, user, LocalDate.now());
    }
}
