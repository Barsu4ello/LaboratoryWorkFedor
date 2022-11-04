package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.model.RoleEntity;
import com.cvetkov.fedor.laboratoryWork.util.enums.Role;
import com.cvetkov.fedor.laboratoryWork.util.enums.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest extends LaboratoryWorkApplicationTests {

    private final UserController userController;

    @Autowired
    public UserControllerTest(UserController userController) {
        this.userController = userController;
    }

    @Test
    void listWithAllUsersShouldBeNotEmptyAfterAddUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        List<UserResponse> users = userController.getAllUsers();

        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    void pageWithAllUsersShouldBeNotEmptyAfterAddUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<UserResponse> users = userController.getAllUsers(sourcePageable);

        Assertions.assertTrue(users.getTotalPages() > 0);
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    void listSizeUsersAfterAddAuthorShouldBeContainsThisUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);
        List<UserResponse> users = userController.getAllUsers();

        Assertions.assertTrue(users.stream()
                .map(UserResponse::getUsername)
                .collect(Collectors.toList())
                .contains("test name"));

        Assertions.assertTrue(users.stream()
                .map(UserResponse::getRole)
                .map(RoleEntity::getRole)
                .collect(Collectors.toList())
                .contains(Role.USER));

        Assertions.assertTrue(users.stream()
                .map(UserResponse::getAuthor)
                .collect(Collectors.toList())
                .contains(1L));
    }

    @Test
    void dbShouldBeReturnUserWithIdOne() {
        UserResponse userResponse = userController.getUserById(1L);

        Assertions.assertEquals(1L, userResponse.getId());
        Assertions.assertEquals("Aleksandr", userResponse.getUsername());
        Assertions.assertEquals(Role.ADMIN, userResponse.getRole().getRole());
        Assertions.assertEquals(1L, userResponse.getAuthor());
    }

    @Test
    void userShouldBeChangedAfterUpdate() {
        UserUpdate userUpdate = new UserUpdate();
        userUpdate.setId(1L);
        userUpdate.setUsername("update");
        userUpdate.setPassword("password");
        userUpdate.setRoleId(2);
        userUpdate.setAuthorId(2L);
        userController.updateUser(userUpdate);
        UserResponse userResponse = userController.getUserById(1L);

        Assertions.assertEquals("update", userResponse.getUsername());
        Assertions.assertEquals(2L, userResponse.getAuthor());
        Assertions.assertEquals(Role.USER, userResponse.getRole().getRole());
    }

    @Test
    void listUsersInDbShouldBeEqualsListUsersAfterDeleteUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        List<UserResponse> responses = userController.getAllUsers();
        int size = responses.size();
        userController.deleteUser(1L);
        int sizeAfterRemove = userController.getAllUsers().size();

        Assertions.assertEquals(size, sizeAfterRemove);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> userController.getUserById(10L));
    }

    @Test
    void passwordMustBeConvertAfterAddUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        List<UserResponse> users = userController.getAllUsers();

        Assertions.assertFalse(users.stream()
                .map(UserResponse::getPassword)
                .collect(Collectors.toList())
                .contains("password"));
    }
}
