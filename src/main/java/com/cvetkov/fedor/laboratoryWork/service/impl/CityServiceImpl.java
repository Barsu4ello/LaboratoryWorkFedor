package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.repository.CityRepository;
import com.cvetkov.fedor.laboratoryWork.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
}
