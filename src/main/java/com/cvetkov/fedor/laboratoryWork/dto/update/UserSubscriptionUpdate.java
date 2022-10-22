package com.cvetkov.fedor.laboratoryWork.dto.update;

import com.cvetkov.fedor.laboratoryWork.model.Subscription;
import com.cvetkov.fedor.laboratoryWork.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionUpdate {

    private Long id;

    @NotNull(message = "IsValid is mandatory")
    private boolean isValid;

    @NotNull(message = "Subscription is mandatory")
    private Subscription subscription;

    @NotNull(message = "User is mandatory")
    private User user;
}
