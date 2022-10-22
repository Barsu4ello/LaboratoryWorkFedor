package com.cvetkov.fedor.laboratoryWork.dto.response;

import com.cvetkov.fedor.laboratoryWork.model.Subscription;
import com.cvetkov.fedor.laboratoryWork.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionResponse {

    private Long id;
    private boolean isValid;
    private Subscription subscription;
    private User user;
}
