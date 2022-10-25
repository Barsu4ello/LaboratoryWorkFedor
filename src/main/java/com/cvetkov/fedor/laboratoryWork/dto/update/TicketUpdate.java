package com.cvetkov.fedor.laboratoryWork.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdate {

    private Long id;

    @Positive(message = "Price must be greater than zero")
    private Integer price;

    @NotNull(message = "Concert is mandatory")
    private Long concertId;

    private Long userId;
}
