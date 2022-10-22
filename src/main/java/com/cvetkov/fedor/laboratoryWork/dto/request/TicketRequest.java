package com.cvetkov.fedor.laboratoryWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {

    @Positive(message = "Price must be greater than zero")
    private Integer price;

    @NotNull(message = "Concert is mandatory")
    private Long concert;

    private Long user;
}
