package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserSubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserSubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserSubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.UserSubscriptionMapper;
import com.cvetkov.fedor.laboratoryWork.repository.UserSubscriptionRepository;
import com.cvetkov.fedor.laboratoryWork.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserSubscriptionMapper userSubscriptionMapper;

    @Override
    public Page<UserSubscriptionResponse> getAllPage(Pageable pageable) {
        return userSubscriptionMapper.userSubscriptionTUserSubscriptionResponsePage(userSubscriptionRepository.findAll(pageable));
    }

    @Override
    public List<UserSubscriptionResponse> getAllList() {
        return userSubscriptionMapper.userSubscriptionToUserSubscriptionResponseList(userSubscriptionRepository.findAll());
    }

    @Override
    public UserSubscriptionResponse findById(Long id) {
        return userSubscriptionMapper
                .userSubscriptionToUserSubscriptionResponse(userSubscriptionRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("UserSubscription with id " + id + " not found")));
    }

    @Override
    public void save(UserSubscriptionRequest userSubscriptionRequest) {
        userSubscriptionRepository.save(userSubscriptionMapper.userSubscriptionRequestToUserSubscription(userSubscriptionRequest));
    }

    @Override
    public void update(UserSubscriptionUpdate userSubscriptionUpdate) {
        userSubscriptionRepository.save(userSubscriptionMapper.userSubscriptionUpdateToUserSubscription(userSubscriptionUpdate));
    }

    @Override
    public void deleteById(Long id) {
        userSubscriptionRepository.deleteById(id);
    }
}
