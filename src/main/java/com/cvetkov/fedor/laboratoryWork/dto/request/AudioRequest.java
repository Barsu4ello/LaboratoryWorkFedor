package com.cvetkov.fedor.laboratoryWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioRequest {

    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private byte[] audio;

    @NotNull(message = "Author is mandatory")
    private Long authorId;
}
