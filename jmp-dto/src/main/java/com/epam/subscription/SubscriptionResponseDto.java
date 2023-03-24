package com.epam.subscription;

/**
 * @author Dominik_Janiga
 */
class SubscriptionResponseDto {

    private Long id;
    private Long userId;
    private String startDate;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public SubscriptionResponseDto(Long id, Long userId, String startDate) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
    }
}
