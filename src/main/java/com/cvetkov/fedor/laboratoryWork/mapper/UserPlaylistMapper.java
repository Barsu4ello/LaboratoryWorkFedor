package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserPlaylistRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserPlaylistResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserPlaylistUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserUpdate;
import com.cvetkov.fedor.laboratoryWork.model.User;
import com.cvetkov.fedor.laboratoryWork.model.UserPlaylist;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPlaylistMapper {

    private final ModelMapper mapper;

    public UserPlaylistResponse userPlaylistToUserPlaylistResponse(UserPlaylist userPlaylist) {
        return mapper.map(userPlaylist, UserPlaylistResponse.class);
    }

    public UserPlaylist userPlaylistRequestToUserPlaylist(UserPlaylistRequest request) {
        return mapper.map(request, UserPlaylist.class);
    }

    public UserPlaylist userPlaylistUpdateToUserPlaylist(UserPlaylistUpdate update) {
        return mapper.map(update, UserPlaylist.class);
    }

    public List<UserPlaylistResponse> userPlaylistToUserPlaylistResponseList(List<UserPlaylist> userPlaylist) {
        return userPlaylist
                .stream()
                .map(this::userPlaylistToUserPlaylistResponse)
                .collect(Collectors.toList());
    }

    public Page<UserPlaylistResponse> userPlaylistToUserPlaylistResponsePage(Page<UserPlaylist> userPlaylistPage) {
        return userPlaylistPage
                .map(this::userPlaylistToUserPlaylistResponse);
    }
}