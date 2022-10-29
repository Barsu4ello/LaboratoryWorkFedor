package com.cvetkov.fedor.laboratoryWork.dto.response;

import com.cvetkov.fedor.laboratoryWork.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private RoleEntity role;
    private Long author;
}
