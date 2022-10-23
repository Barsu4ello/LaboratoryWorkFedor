package com.cvetkov.fedor.laboratoryWork.service;

import com.cvetkov.fedor.laboratoryWork.dto.request.CityRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CityResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CityUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {
    Page<CityResponse> getAllPage(Pageable pageable);

    List<CityResponse> getAllList();

    CityResponse findById(Long id);

    void save(CityRequest authorRequest);

    void update(CityUpdate authorUpdate);

    void deleteById(Long id);
}
