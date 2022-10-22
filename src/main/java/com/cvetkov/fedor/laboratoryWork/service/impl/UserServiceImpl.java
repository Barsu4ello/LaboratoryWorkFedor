package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.UserMapper;
import com.cvetkov.fedor.laboratoryWork.mapper.UserPlaylistMapper;
import com.cvetkov.fedor.laboratoryWork.repository.UserRepository;
import com.cvetkov.fedor.laboratoryWork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
        userRepository.save(userMapper.userRequestToUser(userRequest));
    }

    @Override
    public void update(UserUpdate userUpdate) {
        userRepository.save(userMapper.userUpdateToUser(userUpdate));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
