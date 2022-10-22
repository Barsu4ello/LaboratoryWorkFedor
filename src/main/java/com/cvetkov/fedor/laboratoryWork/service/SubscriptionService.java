package com.cvetkov.fedor.laboratoryWork.service;

import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubscriptionService {
    Page<SubscriptionResponse> getAllPage(Pageable pageable);

    List<SubscriptionResponse> getAllList();

    SubscriptionResponse findById(Long id);

    void save(SubscriptionRequest subscriptionRequest);

    void update(SubscriptionUpdate subscriptionUpdate);

    void deleteById(Long id);
}
