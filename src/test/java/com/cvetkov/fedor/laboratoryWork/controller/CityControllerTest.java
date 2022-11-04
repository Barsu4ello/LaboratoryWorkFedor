package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.CityRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CityResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CityUpdate;
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

public class CityControllerTest extends LaboratoryWorkApplicationTests {

    private final CityController cityController;

    @Autowired
    public CityControllerTest(CityController cityController) {
        this.cityController = cityController;
    }

    @Test
    void listWithAllCitiesShouldBeNotEmptyAfterAddCity() {
        CityRequest cityRequest = new CityRequest();
        cityRequest.setCityName("test name");
        cityRequest.setCountryId(1L);
        cityController.addCity(cityRequest);
        List<CityResponse> cities = cityController.getAllCities();

        Assertions.assertFalse(cities.isEmpty());
    }

    @Test
    void pageWithAllCitiesShouldBeNotEmptyAfterAddCity() {
        CityRequest cityRequest = new CityRequest();
        cityRequest.setCityName("test name");
        cityRequest.setCountryId(1L);
        cityController.addCity(cityRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<CityResponse> cities = cityController.getAllCities(sourcePageable);

        Assertions.assertTrue(cities.getTotalPages() > 0);
        Assertions.assertFalse(cities.isEmpty());
    }

    @Test
    void listSizeCitiesAfterAddAuthorShouldBeContainsThisCity() {
        CityRequest cityRequest = new CityRequest();
        cityRequest.setCityName("test name");
        cityRequest.setCountryId(1L);
        cityController.addCity(cityRequest);
        List<CityResponse> cities = cityController.getAllCities();

        Assertions.assertTrue(cities.stream()
                .map(CityResponse::getCityName)
                .collect(Collectors.toList())
                .contains("test name"));
    }

    @Test
    void dbShouldBeReturnCityWithIdOne() {
        CityResponse cityResponse = cityController.getCityById(1L);

        Assertions.assertEquals(1L, cityResponse.getId());
        Assertions.assertEquals("SPB", cityResponse.getCityName());
    }

    @Test
    void cityShouldBeChangedAfterUpdate() {
        CityUpdate cityUpdate = new CityUpdate();
        cityUpdate.setId(1L);
        cityUpdate.setCityName("update");
        cityUpdate.setCountryId(1L);
        cityController.updateCity(cityUpdate);
        CityResponse cityResponse = cityController.getCityById(1L);

        Assertions.assertEquals("update", cityResponse.getCityName());
    }

    @Test
    void listCitiesInDbShouldBeLessOneAfterDeleteCity() {
        CityRequest cityRequest = new CityRequest();
        cityRequest.setCityName("test");
        cityRequest.setCountryId(1L);
        cityController.addCity(cityRequest);

        List<CityResponse> responses = cityController.getAllCities();
        int size = responses.size();
        cityController.deleteCity(1L);
        int sizeAfterRemove = cityController.getAllCities().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> cityController.getCityById(10L));
    }
}
