package com.cvetkov.fedor.laboratoryWork.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlaylistResponse {

    private Long id;
    private String title;
    private String description;
    private Long user;
}
