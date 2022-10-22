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

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Audio is mandatory")
    private byte audio;

    @NotNull(message = "Title is mandatory")
    private Long author;
}
