package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.repository.UserRepository;
import com.cvetkov.fedor.laboratoryWork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
}
