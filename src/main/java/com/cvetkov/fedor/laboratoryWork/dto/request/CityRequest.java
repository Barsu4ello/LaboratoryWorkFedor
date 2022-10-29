package com.cvetkov.fedor.laboratoryWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

    private Long id;

    @NotBlank(message = "CityName is mandatory")
    private String cityName;

    @NotNull(message = "Country is mandatory")
    private Long countryId;
}
