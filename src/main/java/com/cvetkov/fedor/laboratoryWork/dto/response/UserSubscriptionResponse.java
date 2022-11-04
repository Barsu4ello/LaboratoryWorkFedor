package com.cvetkov.fedor.laboratoryWork.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionResponse {

    private Long id;
    private boolean isValid;
    private Long subscription;
    private Long user;
}
