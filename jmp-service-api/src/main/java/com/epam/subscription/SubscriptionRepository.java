package com.epam.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dominik_Janiga
 */
@Repository
interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
