package com.cvetkov.fedor.laboratoryWork.service;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserResponse> getAllPage(Pageable pageable);

    List<UserResponse> getAllList();

    UserResponse findById(Long id);

    void save(UserRequest userRequest);

    void update(UserUpdate userUpdate);

    void deleteById(Long id);

     void addAudiosByIdForUser (Long userId, List<Long> audiosId);
}
