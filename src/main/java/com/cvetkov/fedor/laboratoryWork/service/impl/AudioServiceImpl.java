package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.repository.AudioRepository;
import com.cvetkov.fedor.laboratoryWork.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudioServiceImpl implements AudioService {

    private final AudioRepository audioRepository;
}
