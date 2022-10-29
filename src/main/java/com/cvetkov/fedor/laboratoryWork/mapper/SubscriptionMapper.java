package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubscriptionMapper {

    private final ModelMapper mapper;

    public SubscriptionResponse subscriptionToSubscriptionResponse(Subscription subscription) {
        return mapper.map(subscription, SubscriptionResponse.class);
    }

    public Subscription subscriptionRequestToSubscription(SubscriptionRequest request) {
        return mapper.map(request, Subscription.class);
    }

    public Subscription subscriptionUpdateToSubscription(SubscriptionUpdate update) {
        return mapper.map(update, Subscription.class);
    }

    public List<SubscriptionResponse> subscriptionToSubscriptionResponseList(List<Subscription> subscription) {
        return subscription
                .stream()
                .map(this::subscriptionToSubscriptionResponse)
                .collect(Collectors.toList());
    }

    public Page<SubscriptionResponse> subscriptionToSubscriptionResponsePage(Page<Subscription> subscriptionPage) {
        return subscriptionPage
                .map(this::subscriptionToSubscriptionResponse);
    }
}
