package com.cvetkov.fedor.laboratoryWork.security.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "UserName is mandatory")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
