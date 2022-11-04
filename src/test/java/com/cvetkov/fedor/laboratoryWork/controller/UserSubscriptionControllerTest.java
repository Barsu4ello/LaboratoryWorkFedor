package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.UserSubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserSubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserSubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
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

public class UserSubscriptionControllerTest extends LaboratoryWorkApplicationTests {

    private final UserSubscriptionController userSubscriptionController;
    private final UserController userController;

    @Autowired
    public UserSubscriptionControllerTest(UserSubscriptionController userSubscriptionController,
                                          UserController userController) {
        this.userSubscriptionController = userSubscriptionController;
        this.userController = userController;
    }

    @Test
    void listWithAllUserSubscriptionsShouldBeNotEmptyAfterAddUserSubscription() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        UserSubscriptionRequest userSubscriptionRequest = new UserSubscriptionRequest();
        userSubscriptionRequest.setValid(true);
        userSubscriptionRequest.setSubscriptionId(1L);
        Long userId = userController.getAllUsers().stream()
                .filter(user -> user.getUsername().equals("test name"))
                .findFirst()
                .orElseThrow()
                .getId();
        userSubscriptionRequest.setHostUserId(userId);
        userSubscriptionController.addUserSubscription(userSubscriptionRequest);
        List<UserSubscriptionResponse> userSubscriptions = userSubscriptionController.getAllUserSubscriptions();

        Assertions.assertFalse(userSubscriptions.isEmpty());
    }

    @Test
    void pageWithAllUserSubscriptionsShouldBeNotEmptyAfterAddUserSubscription() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        UserSubscriptionRequest userSubscriptionRequest = new UserSubscriptionRequest();
        userSubscriptionRequest.setValid(true);
        userSubscriptionRequest.setSubscriptionId(1L);
        Long userId = userController.getAllUsers().stream()
                .filter(user -> user.getUsername().equals("test name"))
                .findFirst()
                .orElseThrow()
                .getId();
        userSubscriptionRequest.setHostUserId(userId);
        userSubscriptionController.addUserSubscription(userSubscriptionRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<UserSubscriptionResponse> userSubscriptions = userSubscriptionController.getAllUserSubscriptions(sourcePageable);

        Assertions.assertTrue(userSubscriptions.getTotalPages() > 0);
        Assertions.assertFalse(userSubscriptions.isEmpty());
    }

    @Test
    void listSizeUserSubscriptionsAfterAddAuthorShouldBeContainsThisUserSubscription() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        UserSubscriptionRequest userSubscriptionRequest = new UserSubscriptionRequest();
        userSubscriptionRequest.setValid(true);
        userSubscriptionRequest.setSubscriptionId(1L);
        Long userId = userController.getAllUsers().stream()
                .filter(user -> user.getUsername().equals("test name"))
                .findFirst()
                .orElseThrow()
                .getId();
        userSubscriptionRequest.setHostUserId(userId);
        userSubscriptionController.addUserSubscription(userSubscriptionRequest);
        List<UserSubscriptionResponse> userSubscriptions = userSubscriptionController.getAllUserSubscriptions();

        Assertions.assertTrue(userSubscriptions.stream()
                .map(UserSubscriptionResponse::isValid)
                .collect(Collectors.toList())
                .contains(true));
    }

    @Test
    void dbShouldBeReturnUserSubscriptionWithIdOne() {
        UserSubscriptionResponse userSubscriptionResponse = userSubscriptionController.getUserSubscriptionById(1L);

        Assertions.assertEquals(1L, userSubscriptionResponse.getId());
        Assertions.assertTrue(userSubscriptionResponse.isValid());
        Assertions.assertEquals(1L, userSubscriptionResponse.getSubscription());
        Assertions.assertEquals(1L, userSubscriptionResponse.getUser());
    }

    @Test
    void userSubscriptionShouldBeChangedAfterUpdate() {
        UserSubscriptionUpdate userSubscriptionsUpdate = new UserSubscriptionUpdate();
        userSubscriptionsUpdate.setId(1L);
        userSubscriptionsUpdate.setValid(false);
        userSubscriptionsUpdate.setSubscriptionId(2L);
        userSubscriptionsUpdate.setHostUserId(2L);
        userSubscriptionController.updateUserSubscription(userSubscriptionsUpdate);
        UserSubscriptionResponse userSubscriptionResponse = userSubscriptionController.getUserSubscriptionById(1L);

        Assertions.assertFalse(userSubscriptionResponse.isValid());
        Assertions.assertEquals(2L, userSubscriptionResponse.getSubscription());
        Assertions.assertEquals(2L, userSubscriptionResponse.getUser());
    }

    @Test
    void listUserSubscriptionsInDbShouldBeLessOneAfterDeleteUserSubscription() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test name");
        userRequest.setPassword("password");
        userRequest.setStatus(UserStatus.ACTIVE);
        userRequest.setRoleId(2);
        userRequest.setCityId(1L);
        userRequest.setAuthorId(1L);
        userController.addUser(userRequest);

        UserSubscriptionRequest userSubscriptionRequest = new UserSubscriptionRequest();
        userSubscriptionRequest.setValid(true);
        userSubscriptionRequest.setSubscriptionId(1L);
        Long userId = userController.getAllUsers().stream()
                .filter(user -> user.getUsername().equals("test name"))
                .findFirst()
                .orElseThrow()
                .getId();
        userSubscriptionRequest.setHostUserId(userId);
        userSubscriptionController.addUserSubscription(userSubscriptionRequest);

        List<UserSubscriptionResponse> responses = userSubscriptionController.getAllUserSubscriptions();
        int size = responses.size();
        userSubscriptionController.deleteUserSubscription(1L);
        int sizeAfterRemove = userSubscriptionController.getAllUserSubscriptions().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> userSubscriptionController.getUserSubscriptionById(10L));
    }
}
