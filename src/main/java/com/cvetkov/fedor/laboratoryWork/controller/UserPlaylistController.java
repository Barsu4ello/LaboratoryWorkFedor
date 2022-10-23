package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserPlaylistRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserPlaylistResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserPlaylistUpdate;
import com.cvetkov.fedor.laboratoryWork.service.UserPlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user-playlist")
public class UserPlaylistController {

    private final UserPlaylistService userPlaylistService;

    @GetMapping
    public Page<UserPlaylistResponse> getAllUserPlaylists(@PageableDefault(size = 5) Pageable pageable) {
        return userPlaylistService.getAllPage(pageable);
    }

    @GetMapping("/all-user-playlist")
    public List<UserPlaylistResponse> getAllUserPlaylists() {
        return userPlaylistService.getAllList();
    }

    @GetMapping("/{id}")
    public UserPlaylistResponse getUserPlaylistById(@PathVariable Long id) {
        return userPlaylistService.findById(id);
    }

    @PostMapping
    public void addUserPlaylist(@Valid @RequestBody UserPlaylistRequest userPlaylistRequest) {
        userPlaylistService.save(userPlaylistRequest);
    }

    @PutMapping
    public void updateUserPlaylist(@Valid @RequestBody UserPlaylistUpdate userPlaylistUpdate) {
        userPlaylistService.update(userPlaylistUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteUserPlaylist(@PathVariable Long id) {
        userPlaylistService.deleteById(id);
    }
}
