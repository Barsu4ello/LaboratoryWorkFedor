package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.CityRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.ConcertRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CityResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.ConcertResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CityUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.ConcertUpdate;
import com.cvetkov.fedor.laboratoryWork.model.City;
import com.cvetkov.fedor.laboratoryWork.model.Concert;
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
public class ConcertMapper {

    private final ModelMapper mapper;

    public ConcertResponse concertToConcertResponse(Concert concert) {
        return mapper.map(concert, ConcertResponse.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Concert.class, ConcertResponse.class)
                .addMappings(m -> m.skip(ConcertResponse::setCity)).setPostConverter(toConcertResponseConverter());
    }

    private Converter<Concert, ConcertResponse> toConcertResponseConverter() {
        return context -> {
            Concert source = context.getSource();
            ConcertResponse destination = context.getDestination();
            destination.setCity(source.getCity().getId());
            return context.getDestination();
        };
    }

    public Concert concertRequestToConcert(ConcertRequest request) {
        return mapper.map(request, Concert.class);
    }

    public Concert concertUpdateToConcert(ConcertUpdate update) {
        return mapper.map(update, Concert.class);
    }

    public List<ConcertResponse> concertToConcertResponseList(List<Concert> concert) {
        return concert
                .stream()
                .map(this::concertToConcertResponse)
                .collect(Collectors.toList());
    }

    public Page<ConcertResponse> concerToConcertResponsePage(Page<Concert> concertPage) {
        return concertPage
                .map(this::concertToConcertResponse);
    }
}
