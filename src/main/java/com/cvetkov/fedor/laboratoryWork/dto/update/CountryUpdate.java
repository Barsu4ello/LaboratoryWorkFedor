package com.cvetkov.fedor.laboratoryWork.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryUpdate {

    private Long id;

    @NotBlank(message = "CountryName is mandatory")
    private String countryName;
}
