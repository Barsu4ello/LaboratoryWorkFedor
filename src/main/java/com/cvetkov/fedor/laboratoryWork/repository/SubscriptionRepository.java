package com.cvetkov.fedor.laboratoryWork.repository;

import com.cvetkov.fedor.laboratoryWork.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
