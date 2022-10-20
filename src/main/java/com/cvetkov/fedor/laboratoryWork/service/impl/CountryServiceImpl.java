package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.repository.CountryRepository;
import com.cvetkov.fedor.laboratoryWork.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
}
