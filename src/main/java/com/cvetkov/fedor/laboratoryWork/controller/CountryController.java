package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.ConcertRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.CountryRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.ConcertResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.CountryResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.ConcertUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.CountryUpdate;
import com.cvetkov.fedor.laboratoryWork.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public Page<CountryResponse> getAllCountries(@PageableDefault(size = 5) Pageable pageable) {
        return countryService.getAllPage(pageable);
    }

    @GetMapping("/all-country")
    public List<CountryResponse> getAllCountries() {
        return countryService.getAllList();
    }

    @GetMapping("/{id}")
    public CountryResponse getCountryById(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @PostMapping
    public void addCountry(@Valid @RequestBody CountryRequest countryRequest) {
        countryService.save(countryRequest);
    }

    @PutMapping
    public void updateCountry(@Valid @RequestBody CountryUpdate countryUpdate) {
        countryService.update(countryUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
