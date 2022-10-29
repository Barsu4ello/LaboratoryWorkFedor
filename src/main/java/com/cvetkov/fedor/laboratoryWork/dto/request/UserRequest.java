package com.cvetkov.fedor.laboratoryWork.dto.request;

import com.cvetkov.fedor.laboratoryWork.util.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Status is mandatory")
    private UserStatus status;

    @NotNull(message =  "Role is mandatory")
    private Integer roleId;

    private Long cityId;

    private Long authorId;
}
