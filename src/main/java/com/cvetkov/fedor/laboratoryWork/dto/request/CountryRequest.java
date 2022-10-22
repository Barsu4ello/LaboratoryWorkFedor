package com.cvetkov.fedor.laboratoryWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequest {

    @NotBlank(message = "CountryName is mandatory")
    private String countryName;
}
