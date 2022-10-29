package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.SubscriptionMapper;
import com.cvetkov.fedor.laboratoryWork.repository.SubscriptionRepository;
import com.cvetkov.fedor.laboratoryWork.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public Page<SubscriptionResponse> getAllPage(Pageable pageable) {
        return subscriptionMapper.subscriptionToSubscriptionResponsePage(subscriptionRepository.findAll(pageable));
    }

    @Override
    public List<SubscriptionResponse> getAllList() {
        return subscriptionMapper.subscriptionToSubscriptionResponseList(subscriptionRepository.findAll());
    }

    @Override
    public SubscriptionResponse findById(Long id) {
        return subscriptionMapper
                .subscriptionToSubscriptionResponse(subscriptionRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Subscription with id " + id + " not found")));
    }

    @Override
    public void save(SubscriptionRequest subscriptionRequest) {
        subscriptionRepository.save(subscriptionMapper.subscriptionRequestToSubscription(subscriptionRequest));

    }

    @Override
    public void update(SubscriptionUpdate subscriptionUpdate) {
        subscriptionRepository.save(subscriptionMapper.subscriptionUpdateToSubscription(subscriptionUpdate));
    }

    @Override
    public void deleteById(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
