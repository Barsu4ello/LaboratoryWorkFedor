package com.cvetkov.fedor.laboratoryWork.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityUpdate {

    private Long id;

    @NotBlank(message = "CityName is mandatory")
    private String cityName;

    @NotNull(message = "Country is mandatory")
    private Long countryId;
}
