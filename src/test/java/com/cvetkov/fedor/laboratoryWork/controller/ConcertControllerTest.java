package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.ConcertRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.ConcertResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.ConcertUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConcertControllerTest extends LaboratoryWorkApplicationTests {

    private final ConcertController concertController;

    @Autowired
    public ConcertControllerTest(ConcertController concertController) {
        this.concertController = concertController;
    }

    @Test
    void listWithAllConcertsShouldBeNotEmptyAfterAddConcert() {
        ConcertRequest concertRequest = new ConcertRequest();
        concertRequest.setName("test name");
        concertRequest.setPerformer("test performer");
        concertRequest.setConcertDate(LocalDateTime.now());
        concertRequest.setCityId(1L);
        concertController.addConcert(concertRequest);
        List<ConcertResponse> concerts = concertController.getAllConcerts();

        Assertions.assertFalse(concerts.isEmpty());
    }

    @Test
    void pageWithAllConcertsShouldBeNotEmptyAfterAddConcert() {
        ConcertRequest concertRequest = new ConcertRequest();
        concertRequest.setName("test name");
        concertRequest.setPerformer("test performer");
        concertRequest.setConcertDate(LocalDateTime.now());
        concertRequest.setCityId(1L);
        concertController.addConcert(concertRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<ConcertResponse> concerts = concertController.getAllConcerts(sourcePageable);

        Assertions.assertTrue(concerts.getTotalPages() > 0);
        Assertions.assertFalse(concerts.isEmpty());
    }

    @Test
    void listSizeConcertsAfterAddAuthorShouldBeContainsThisConcert() {
        ConcertRequest concertRequest = new ConcertRequest();
        concertRequest.setName("test name");
        concertRequest.setPerformer("test performer");
        LocalDateTime date = LocalDateTime.now();
        concertRequest.setConcertDate(date);
        concertRequest.setCityId(1L);
        concertController.addConcert(concertRequest);
        List<ConcertResponse> concerts = concertController.getAllConcerts();

        Assertions.assertTrue(concerts.stream()
                .map(ConcertResponse::getName)
                .collect(Collectors.toList())
                .contains("test name"));

        Assertions.assertTrue(concerts.stream()
                .map(ConcertResponse::getPerformer)
                .collect(Collectors.toList())
                .contains("test performer"));

        Assertions.assertTrue(concerts.stream()
                .map(ConcertResponse::getConcertDate)
                .collect(Collectors.toList())
                .contains(date));
    }

    @Test
    void dbShouldBeReturnConcertWithIdOne() {
        ConcertResponse concertResponse = concertController.getConcertById(1L);

        Assertions.assertEquals(1L, concertResponse.getId());
        Assertions.assertEquals("First LP Concert", concertResponse.getName());
        Assertions.assertEquals(LocalDateTime.of(2000, 1,1,0,0,0), concertResponse.getConcertDate());
    }

    @Test
    void concertShouldBeChangedAfterUpdate() {
        ConcertUpdate concertUpdate = new ConcertUpdate();
        concertUpdate.setId(1L);
        concertUpdate.setName("update");
        concertUpdate.setCityId(1L);
        concertController.updateConcert(concertUpdate);
        ConcertResponse concertResponse = concertController.getConcertById(1L);

        Assertions.assertEquals("update", concertResponse.getName());
        Assertions.assertEquals(1, concertResponse.getCity());
    }

    @Test
    void listConcertsInDbShouldBeLessOneAfterDeleteConcert() {
        ConcertRequest concertRequest = new ConcertRequest();
        concertRequest.setName("test name");
        concertRequest.setPerformer("test performer");
        concertRequest.setConcertDate(LocalDateTime.now());
        concertRequest.setCityId(1L);
        concertController.addConcert(concertRequest);

        List<ConcertResponse> responses = concertController.getAllConcerts();
        int size = responses.size();
        concertController.deleteConcert(1L);
        int sizeAfterRemove = concertController.getAllConcerts().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> concertController.getConcertById(10L));
    }
}
