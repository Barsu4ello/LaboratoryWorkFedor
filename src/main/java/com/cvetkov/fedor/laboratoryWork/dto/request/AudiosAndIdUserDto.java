package com.cvetkov.fedor.laboratoryWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudiosAndIdUserDto {

    @NotNull(message = "userId is mandatory")
    private Long userId;

    @NotNull(message = "audiosId is mandatory")
    private List<Long> audiosId;
}
