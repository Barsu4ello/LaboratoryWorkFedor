package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserUpdate;
import com.cvetkov.fedor.laboratoryWork.model.User;
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
public class UserMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserResponse.class)
                .addMappings(m -> m.skip(UserResponse::setAuthor)).setPostConverter(toUserResponseConverter());
    }

    private Converter<User, UserResponse> toUserResponseConverter() {
        return context -> {
            User source = context.getSource();
            UserResponse destination = context.getDestination();
            destination.setAuthor(source.getAuthor().getId());
            return context.getDestination();
        };
    }

    public UserResponse userToUserResponse(User user) {
        return mapper.map(user, UserResponse.class);
    }

    public User userRequestToUser(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public User userUpdateToUser(UserUpdate update) {
        return mapper.map(update, User.class);
    }

    public List<UserResponse> userToUserResponseList(List<User> user) {
        return user
                .stream()
                .map(this::userToUserResponse)
                .collect(Collectors.toList());
    }

    public Page<UserResponse> userToUserResponsePage(Page<User> userPage) {
        return userPage
                .map(this::userToUserResponse);
    }
}
