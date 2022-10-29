package com.cvetkov.fedor.laboratoryWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Positive(message = "Price must be greater than zero")
    private Integer price;

    @NotBlank(message = "Description is mandatory")
    private String description;
}
