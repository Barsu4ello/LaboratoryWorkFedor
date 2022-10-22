package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.AuthorRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.CityRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AuthorResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.CityResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AuthorUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.CityUpdate;
import com.cvetkov.fedor.laboratoryWork.model.Author;
import com.cvetkov.fedor.laboratoryWork.model.City;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityMapper {

    private final ModelMapper mapper;

    public CityResponse cityToCityResponse(City city) {
        return mapper.map(city, CityResponse.class);
    }

    public City cityRequestToCity(CityRequest request) {
        return mapper.map(request, City.class);
    }

    public City cityUpdateToCity(CityUpdate update) {
        return mapper.map(update, City.class);
    }

    public List<CityResponse> cityToCityResponseList(List<City> city) {
        return city
                .stream()
                .map(this::cityToCityResponse)
                .collect(Collectors.toList());
    }

    public Page<CityResponse> cityToCityResponsePage(Page<City> cityPage) {
        return cityPage
                .map(this::cityToCityResponse);
    }
}
