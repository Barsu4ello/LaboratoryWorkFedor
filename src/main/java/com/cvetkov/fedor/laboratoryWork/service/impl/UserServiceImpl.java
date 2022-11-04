package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.UserMapper;
import com.cvetkov.fedor.laboratoryWork.model.Audio;
import com.cvetkov.fedor.laboratoryWork.model.User;
import com.cvetkov.fedor.laboratoryWork.repository.AudioRepository;
import com.cvetkov.fedor.laboratoryWork.repository.UserRepository;
import com.cvetkov.fedor.laboratoryWork.service.UserService;
import com.cvetkov.fedor.laboratoryWork.util.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AudioRepository audioRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public Page<UserResponse> getAllPage(Pageable pageable) {
        return userMapper.userToUserResponsePage(userRepository.findAll(pageable));
    }

    @Override
    public List<UserResponse> getAllList() {
        return userMapper.userToUserResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse findById(Long id) {
        return userMapper
                .userToUserResponse(userRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " not found")));
    }

    @Override
    public void save(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(UserUpdate userUpdate) {
        User user = userMapper.userUpdateToUser(userUpdate);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userMapper.userUpdateToUser(userUpdate));
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " not found"));
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addAudiosByIdForUser(Long userId, List<Long> audiosId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User with id " + userId + " not found"));
        List<Long> audiosIdByUser = user.getAudios().stream().map(Audio::getId).collect(Collectors.toList());
        audiosId.removeAll(audiosIdByUser);
        List<Audio> audios = audioRepository.findAllById(audiosId);
        user.getAudios().addAll(audios);
        userRepository.save(user);
    }
}
