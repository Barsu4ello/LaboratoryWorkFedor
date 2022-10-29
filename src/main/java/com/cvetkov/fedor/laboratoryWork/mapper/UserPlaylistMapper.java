package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserPlaylistRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserPlaylistResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserPlaylistUpdate;
import com.cvetkov.fedor.laboratoryWork.model.UserPlaylist;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPlaylistMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UserPlaylist.class, UserPlaylistResponse.class)
                .addMappings(m -> m.skip(UserPlaylistResponse::setUser))
                .setPostConverter(toUserPlaylistResponseConverter());
    }

    private Converter<UserPlaylist, UserPlaylistResponse> toUserPlaylistResponseConverter() {
        return context -> {
            UserPlaylist source = context.getSource();
            UserPlaylistResponse destination = context.getDestination();
            destination.setUser(source.getUser().getId());
            return context.getDestination();
        };
    }

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
