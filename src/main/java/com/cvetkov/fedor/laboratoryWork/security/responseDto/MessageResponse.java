package com.cvetkov.fedor.laboratoryWork.security.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

    @NotBlank(message = "Message is mandatory")
    private String message;
}
