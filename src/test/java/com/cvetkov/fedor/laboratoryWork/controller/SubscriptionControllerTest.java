package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubscriptionControllerTest extends LaboratoryWorkApplicationTests {

    private final  SubscriptionController subscriptionController;

    @Autowired
    public SubscriptionControllerTest(SubscriptionController subscriptionController) {
        this.subscriptionController = subscriptionController;
    }

    @Test
    void listWithAllSubscriptionsShouldBeNotEmptyAfterAddSubscription() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setName("test name");
        subscriptionRequest.setDescription("test description");
        subscriptionRequest.setPrice(100);
        subscriptionController.addSubscription(subscriptionRequest);

        List<SubscriptionResponse> subscriptions = subscriptionController.getAllSubscriptions();

        Assertions.assertFalse(subscriptions.isEmpty());
    }

    @Test
    void priceInRequestMustBePositive() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setName("test name");
        subscriptionRequest.setDescription("test description");
        subscriptionRequest.setPrice(-100);
        Assertions.assertThrows(ConstraintViolationException.class, () -> subscriptionController.addSubscription(subscriptionRequest));
    }

    @Test
    void pageWithAllSubscriptionsShouldBeNotEmptyAfterAddSubscription() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setName("test name");
        subscriptionRequest.setDescription("test description");
        subscriptionRequest.setPrice(100);
        subscriptionController.addSubscription(subscriptionRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<SubscriptionResponse> subscriptions = subscriptionController.getAllSubscriptions(sourcePageable);

        Assertions.assertTrue(subscriptions.getTotalPages() > 0);
        Assertions.assertFalse(subscriptions.isEmpty());
    }

    @Test
    void listSizeSubscriptionsAfterAddAuthorShouldBeContainsThisSubscription() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setName("test name");
        subscriptionRequest.setDescription("test description");
        subscriptionRequest.setPrice(100);
        subscriptionController.addSubscription(subscriptionRequest);
        List<SubscriptionResponse> subscriptions = subscriptionController.getAllSubscriptions();

        Assertions.assertTrue(subscriptions.stream()
                .map(SubscriptionResponse::getName)
                .collect(Collectors.toList())
                .contains("test name"));

        Assertions.assertTrue(subscriptions.stream()
                .map(SubscriptionResponse::getDescription)
                .collect(Collectors.toList())
                .contains("test description"));

        Assertions.assertTrue(subscriptions.stream()
                .map(SubscriptionResponse::getPrice)
                .collect(Collectors.toList())
                .contains(100));
    }

    @Test
    void dbShouldBeReturnSubscriptionWithIdOne() {
        SubscriptionResponse subscriptionResponse = subscriptionController.getSubscriptionById(1L);

        Assertions.assertEquals(1L, subscriptionResponse.getId());
        Assertions.assertEquals("First subscription", subscriptionResponse.getName());
        Assertions.assertEquals("First subscription description", subscriptionResponse.getDescription());
        Assertions.assertEquals(1000, subscriptionResponse.getPrice());
    }

    @Test
    void subscriptionShouldBeChangedAfterUpdate() {
        SubscriptionUpdate subscriptionUpdate = new SubscriptionUpdate();
        subscriptionUpdate.setId(1L);
        subscriptionUpdate.setName("update");
        subscriptionController.updateSubscription(subscriptionUpdate);

        SubscriptionResponse subscriptionResponse = subscriptionController.getSubscriptionById(1L);

        Assertions.assertEquals("update", subscriptionResponse.getName());
    }

    @Test
    void listSubscriptionsInDbShouldBeLessOneAfterDeleteSubscription() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setName("test name");
        subscriptionRequest.setDescription("test description");
        subscriptionRequest.setPrice(100);
        subscriptionController.addSubscription(subscriptionRequest);

        List<SubscriptionResponse> responses = subscriptionController.getAllSubscriptions();
        int size = responses.size();
        subscriptionController.deleteSubscription(1L);
        int sizeAfterRemove = subscriptionController.getAllSubscriptions().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> subscriptionController.getSubscriptionById(10L));
    }
}
