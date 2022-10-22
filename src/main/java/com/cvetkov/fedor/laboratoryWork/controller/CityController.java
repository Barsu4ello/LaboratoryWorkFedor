package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.AuthorRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.CityRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AuthorResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.CityResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AuthorUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.CityUpdate;
import com.cvetkov.fedor.laboratoryWork.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/city")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public Page<CityResponse> getAllCities(@PageableDefault(size = 5) Pageable pageable) {
        return cityService.getAllPage(pageable);
    }

    @GetMapping("/all-city")
    public List<CityResponse> getAllCities() {
        return cityService.getAllList();
    }

    @GetMapping("/{id}")
    public CityResponse getCityById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    public void addCity(@Valid @RequestBody CityRequest cityRequest) {
        cityService.save(cityRequest);
    }

    @PutMapping
    public void updateCity(@Valid @RequestBody CityUpdate cityUpdate) {
        cityService.update(cityUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteById(id);
    }
}
