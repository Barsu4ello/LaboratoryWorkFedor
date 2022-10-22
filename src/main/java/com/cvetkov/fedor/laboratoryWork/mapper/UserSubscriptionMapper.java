package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserPlaylistRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.UserSubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserPlaylistResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserSubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserPlaylistUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserSubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.model.UserPlaylist;
import com.cvetkov.fedor.laboratoryWork.model.UserSubscription;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserSubscriptionMapper {

    private final ModelMapper mapper;

    public UserSubscriptionResponse userSubscriptionToUserSubscriptionResponse(UserSubscription userSubscription) {
        return mapper.map(userSubscription, UserSubscriptionResponse.class);
    }

    public UserSubscription userSubscriptionRequestToUserSubscription(UserSubscriptionRequest request) {
        return mapper.map(request, UserSubscription.class);
    }

    public UserSubscription userSubscriptionUpdateToUserSubscription(UserSubscriptionUpdate update) {
        return mapper.map(update, UserSubscription.class);
    }

    public List<UserSubscriptionResponse> userSubscriptionToUserSubscriptionResponseList(List<UserSubscription> userSubscription) {
        return userSubscription
                .stream()
                .map(this::userSubscriptionToUserSubscriptionResponse)
                .collect(Collectors.toList());
    }

    public Page<UserSubscriptionResponse> userSubscriptionTUserSubscriptionResponsePage(Page<UserSubscription> userSubscriptionPage) {
        return userSubscriptionPage
                .map(this::userSubscriptionToUserSubscriptionResponse);
    }

}
