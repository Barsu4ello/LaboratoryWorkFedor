package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.CountryRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CountryResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CountryUpdate;
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

public class CountryControllerTest extends LaboratoryWorkApplicationTests {
    private final CountryController countryController;

    @Autowired
    public CountryControllerTest(CountryController countryController) {
        this.countryController = countryController;
    }

    @Test
    void listWithAllCountriesShouldBeNotEmptyAfterAddCountry() {
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setCountryName("test name");
        List<CountryResponse> countries = countryController.getAllCountries();

        Assertions.assertFalse(countries.isEmpty());
    }

    @Test
    void pageWithAllCountriesShouldBeNotEmptyAfterAddCountry() {
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setCountryName("test name");
        countryController.addCountry(countryRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<CountryResponse> countries = countryController.getAllCountries(sourcePageable);

        Assertions.assertTrue(countries.getTotalPages() > 0);
        Assertions.assertFalse(countries.isEmpty());
    }

    @Test
    void listSizeCountriesAfterAddAuthorShouldBeContainsThisCountry() {
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setCountryName("test name");
        countryController.addCountry(countryRequest);
        List<CountryResponse> countries = countryController.getAllCountries();

        Assertions.assertTrue(countries.stream()
                .map(CountryResponse::getCountryName)
                .collect(Collectors.toList())
                .contains("test name"));
    }

    @Test
    void dbShouldBeReturnCountryWithIdOne() {
        CountryResponse countryResponse = countryController.getCountryById(1L);

        Assertions.assertEquals(1L, countryResponse.getId());
        Assertions.assertEquals("Russian", countryResponse.getCountryName());
    }

    @Test
    void countryShouldBeChangedAfterUpdate() {
        CountryUpdate countryUpdate = new CountryUpdate();
        countryUpdate.setId(1L);
        countryUpdate.setCountryName("update");

        countryController.updateCountry(countryUpdate);
        CountryResponse countryResponse = countryController.getCountryById(1L);

        Assertions.assertEquals("update", countryResponse.getCountryName());
    }

    @Test
    void listCountriesInDbShouldBeLessOneAfterDeleteCountry() {
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setCountryName("test");
        countryController.addCountry(countryRequest);

        List<CountryResponse> responses = countryController.getAllCountries();
        int size = responses.size();
        countryController.deleteCountry(1L);
        int sizeAfterRemove = countryController.getAllCountries().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> countryController.getCountryById(10L));
    }
}
