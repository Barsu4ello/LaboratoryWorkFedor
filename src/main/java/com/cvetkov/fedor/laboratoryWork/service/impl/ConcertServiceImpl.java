package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.repository.ConcertRepository;
import com.cvetkov.fedor.laboratoryWork.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
}
