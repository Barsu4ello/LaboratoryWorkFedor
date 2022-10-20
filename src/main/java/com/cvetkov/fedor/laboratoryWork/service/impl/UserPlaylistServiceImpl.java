package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.repository.UserPlaylistRepository;
import com.cvetkov.fedor.laboratoryWork.service.UserPlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPlaylistServiceImpl implements UserPlaylistService {

    private final UserPlaylistRepository userPlaylistRepository;
}
