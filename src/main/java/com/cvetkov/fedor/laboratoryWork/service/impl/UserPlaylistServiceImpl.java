package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserPlaylistRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserPlaylistResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserPlaylistUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.UserPlaylistMapper;
import com.cvetkov.fedor.laboratoryWork.repository.UserPlaylistRepository;
import com.cvetkov.fedor.laboratoryWork.service.UserPlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPlaylistServiceImpl implements UserPlaylistService {
    private final UserPlaylistRepository userPlaylistRepository;
    private final UserPlaylistMapper userPlaylistMapper;

    @Override
    public Page<UserPlaylistResponse> getAllPage(Pageable pageable) {
        return userPlaylistMapper.userPlaylistToUserPlaylistResponsePage(userPlaylistRepository.findAll(pageable));
    }

    @Override
    public List<UserPlaylistResponse> getAllList() {
        return userPlaylistMapper.userPlaylistToUserPlaylistResponseList(userPlaylistRepository.findAll());
    }

    @Override
    public UserPlaylistResponse findById(Long id) {
        return userPlaylistMapper
                .userPlaylistToUserPlaylistResponse(userPlaylistRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("UserPlaylist with id " + id + " not found")));
    }

    @Override
    public void save(UserPlaylistRequest userPlaylistRequest) {
        userPlaylistRepository.save(userPlaylistMapper.userPlaylistRequestToUserPlaylist(userPlaylistRequest));
    }

    @Override
    public void update(UserPlaylistUpdate userPlaylistUpdate) {
        userPlaylistRepository.save(userPlaylistMapper.userPlaylistUpdateToUserPlaylist(userPlaylistUpdate));
    }

    @Override
    public void deleteById(Long id) {
        userPlaylistRepository.deleteById(id);
    }
}
