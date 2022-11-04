package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.UserPlaylistRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserPlaylistResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserPlaylistUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserPlaylistControllerTest extends LaboratoryWorkApplicationTests {

    private final UserPlaylistController userPlaylistController;

    @Autowired
    public UserPlaylistControllerTest(UserPlaylistController userPlaylistController) {
        this.userPlaylistController = userPlaylistController;
    }

    @Test
    void listWithAllUserPlaylistsShouldBeNotEmptyAfterAddUserPlaylist() {
        UserPlaylistRequest userPlaylistRequest = new UserPlaylistRequest();
        userPlaylistRequest.setTitle("test title");
        userPlaylistRequest.setDescription("test description");
        userPlaylistRequest.setUserId(1L);
        userPlaylistController.addUserPlaylist(userPlaylistRequest);
        List<UserPlaylistResponse> userPlaylists = userPlaylistController.getAllUserPlaylists();

        Assertions.assertFalse(userPlaylists.isEmpty());
    }

    @Test
    void pageWithAllUserPlaylistsShouldBeNotEmptyAfterAddUserPlaylist() {
        UserPlaylistRequest userPlaylistRequest = new UserPlaylistRequest();
        userPlaylistRequest.setTitle("test title");
        userPlaylistRequest.setDescription("test description");
        userPlaylistRequest.setUserId(1L);
        userPlaylistController.addUserPlaylist(userPlaylistRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<UserPlaylistResponse> userPlaylists = userPlaylistController.getAllUserPlaylists(sourcePageable);

        Assertions.assertTrue(userPlaylists.getTotalPages() > 0);
        Assertions.assertFalse(userPlaylists.isEmpty());
    }

    @Test
    void listSizeUserPlaylistsAfterAddAuthorShouldBeContainsThisUserPlaylist() {
        UserPlaylistRequest userPlaylistRequest = new UserPlaylistRequest();
        userPlaylistRequest.setTitle("test title");
        userPlaylistRequest.setDescription("test description");
        userPlaylistRequest.setUserId(1L);
        userPlaylistController.addUserPlaylist(userPlaylistRequest);
        List<UserPlaylistResponse> userPlaylists = userPlaylistController.getAllUserPlaylists();

        Assertions.assertTrue(userPlaylists.stream()
                .map(UserPlaylistResponse::getTitle)
                .collect(Collectors.toList())
                .contains("test title"));

        Assertions.assertTrue(userPlaylists.stream()
                .map(UserPlaylistResponse::getDescription)
                .collect(Collectors.toList())
                .contains("test description"));
    }

    @Test
    void dbShouldBeReturnUserPlaylistWithIdOne() {
        UserPlaylistResponse userPlaylistResponse = userPlaylistController.getUserPlaylistById(1L);

        Assertions.assertEquals(1L, userPlaylistResponse.getId());
        Assertions.assertEquals("Aleksandr playlist1", userPlaylistResponse.getTitle());
        Assertions.assertEquals("Aleksandr playlist", userPlaylistResponse.getDescription());
        Assertions.assertEquals(1L, userPlaylistResponse.getUser());
    }

    @Test
    void userPlaylistShouldBeChangedAfterUpdate() {
        UserPlaylistUpdate userPlaylistUpdate = new UserPlaylistUpdate();
        userPlaylistUpdate.setId(1L);
        userPlaylistUpdate.setTitle("update");
        userPlaylistUpdate.setUserId(2L);
        userPlaylistController.updateUserPlaylist(userPlaylistUpdate);
        UserPlaylistResponse cityResponse = userPlaylistController.getUserPlaylistById(1L);

        Assertions.assertEquals("update", cityResponse.getTitle());
        Assertions.assertEquals(2L, cityResponse.getUser());
    }

    @Test
    void listUserPlaylistsInDbShouldBeLessOneAfterDeleteUserPlaylist() {
        UserPlaylistRequest userPlaylistRequest = new UserPlaylistRequest();
        userPlaylistRequest.setTitle("test title");
        userPlaylistRequest.setDescription("test description");
        userPlaylistRequest.setUserId(1L);
        userPlaylistController.addUserPlaylist(userPlaylistRequest);

        List<UserPlaylistResponse> responses = userPlaylistController.getAllUserPlaylists();
        int size = responses.size();
        userPlaylistController.deleteUserPlaylist(1L);
        int sizeAfterRemove = userPlaylistController.getAllUserPlaylists().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> userPlaylistController.getUserPlaylistById(10L));
    }
}
