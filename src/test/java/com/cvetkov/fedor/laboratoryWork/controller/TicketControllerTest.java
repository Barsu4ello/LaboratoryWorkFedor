package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
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

public class TicketControllerTest extends LaboratoryWorkApplicationTests {

    private final TicketController ticketController;

    @Autowired
    public TicketControllerTest(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @Test
    void listWithAllTicketsShouldBeNotEmptyAfterAddTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setPrice(100);
        ticketRequest.setConcertId(1L);
        ticketRequest.setUserId(1L);
        ticketController.addTicket(ticketRequest);

        List<TicketResponse> tickets = ticketController.getAllTickets();

        Assertions.assertFalse(tickets.isEmpty());
    }

    @Test
    void pageWithAllTicketsShouldBeNotEmptyAfterAddTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setPrice(100);
        ticketRequest.setConcertId(1L);
        ticketRequest.setUserId(1L);
        ticketController.addTicket(ticketRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<TicketResponse> tickets = ticketController.getAllTickets(sourcePageable);

        Assertions.assertTrue(tickets.getTotalPages() > 0);
        Assertions.assertFalse(tickets.isEmpty());
    }

    @Test
    void listSizeTicketsAfterAddAuthorShouldBeContainsThisTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setPrice(100);
        ticketRequest.setConcertId(1L);
        ticketRequest.setUserId(1L);
        ticketController.addTicket(ticketRequest);

        List<TicketResponse> tickets = ticketController.getAllTickets();

        Assertions.assertTrue(tickets.stream()
                .map(TicketResponse::getPrice)
                .collect(Collectors.toList())
                .contains(100));
    }

    @Test
    void priceInRequestMustBePositive() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setPrice(-100);
        ticketRequest.setConcertId(1L);
        ticketRequest.setUserId(1L);
        Assertions.assertThrows(ConstraintViolationException.class, () -> ticketController.addTicket(ticketRequest));
    }

    @Test
    void dbShouldBeReturnTicketWithIdOne() {
        TicketResponse ticketResponse = ticketController.getTicketById(1L);

        Assertions.assertEquals(1L, ticketResponse.getId());
        Assertions.assertEquals(200, ticketResponse.getPrice());
    }

    @Test
    void ticketShouldBeChangedAfterUpdate() {
        TicketUpdate ticketUpdate = new TicketUpdate();
        ticketUpdate.setId(1L);
        ticketUpdate.setPrice(1000);
        ticketUpdate.setUserId(2L);
        ticketUpdate.setConcertId(2L);
        ticketController.updateTicket(ticketUpdate);
        TicketResponse ticketResponse = ticketController.getTicketById(1L);

        Assertions.assertEquals(1000, ticketResponse.getPrice());
        Assertions.assertEquals(2L, ticketResponse.getConcert());
        Assertions.assertEquals(2L, ticketResponse.getUser());
    }

    @Test
    void listTicketsInDbShouldBeLessOneAfterDeleteTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setPrice(100);
        ticketRequest.setConcertId(1L);
        ticketRequest.setUserId(1L);
        ticketController.addTicket(ticketRequest);

        List<TicketResponse> responses = ticketController.getAllTickets();
        int size = responses.size();
        ticketController.deleteTicket(1L);
        int sizeAfterRemove = ticketController.getAllTickets().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> ticketController.getTicketById(10L));
    }
}
