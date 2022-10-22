package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.CountryRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CountryResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CountryUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.ConcertMapper;
import com.cvetkov.fedor.laboratoryWork.mapper.CountryMapper;
import com.cvetkov.fedor.laboratoryWork.repository.CountryRepository;
import com.cvetkov.fedor.laboratoryWork.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public Page<CountryResponse> getAllPage(Pageable pageable) {
        return countryMapper.concertToCountryResponsePage(countryRepository.findAll(pageable));
    }

    @Override
    public List<CountryResponse> getAllList() {
        return countryMapper.concertToCountryResponseList(countryRepository.findAll());
    }

    @Override
    public CountryResponse findById(Long id) {
        return countryMapper
                .countryToCountryResponse(countryRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Country with id " + id + " not found")));
    }

    @Override
    public void save(CountryRequest countryRequest) {
        countryRepository.save(countryMapper.countryRequestToCountry(countryRequest));
    }

    @Override
    public void update(CountryUpdate countryUpdate) {
        countryRepository.save(countryMapper.countryUpdateToCountry(countryUpdate));
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
