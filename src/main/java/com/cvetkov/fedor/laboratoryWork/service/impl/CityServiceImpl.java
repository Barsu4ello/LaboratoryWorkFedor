package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.CityRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CityResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CityUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.CityMapper;
import com.cvetkov.fedor.laboratoryWork.repository.CityRepository;
import com.cvetkov.fedor.laboratoryWork.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Page<CityResponse> getAllPage(Pageable pageable) {
        return cityMapper.cityToCityResponsePage(cityRepository.findAll(pageable));
    }

    @Override
    public List<CityResponse> getAllList() {
        return cityMapper.cityToCityResponseList(cityRepository.findAll());
    }

    @Override
    public CityResponse findById(Long id) {
        return cityMapper
                .cityToCityResponse(cityRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("City with id " + id + " not found")));
    }

    @Override
    public void save(CityRequest authorRequest) {
        cityRepository.save(cityMapper.cityRequestToCity(authorRequest));
    }

    @Override
    public void update(CityUpdate authorUpdate) {
        cityRepository.save(cityMapper.cityUpdateToCity(authorUpdate));
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
