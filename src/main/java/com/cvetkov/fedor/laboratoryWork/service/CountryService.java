package com.cvetkov.fedor.laboratoryWork.service;

import com.cvetkov.fedor.laboratoryWork.dto.request.CountryRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CountryResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CountryUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {
    Page<CountryResponse> getAllPage(Pageable pageable);

    List<CountryResponse> getAllList();

    CountryResponse findById(Long id);

    void save(CountryRequest countryRequest);

    void update(CountryUpdate countryUpdate);

    void deleteById(Long id);
}
