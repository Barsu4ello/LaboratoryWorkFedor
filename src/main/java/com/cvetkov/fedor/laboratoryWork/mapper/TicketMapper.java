package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
import com.cvetkov.fedor.laboratoryWork.model.Ticket;
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
public class TicketMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Ticket.class, TicketResponse.class)
                .addMappings(m -> m.skip(TicketResponse::setUser))
                .addMappings(m -> m.skip(TicketResponse::setConcert))
                .setPostConverter(toTicketResponseConverter());
    }

    private Converter<Ticket, TicketResponse> toTicketResponseConverter() {
        return context -> {
            Ticket source = context.getSource();
            TicketResponse destination = context.getDestination();
            destination.setUser(source.getUser().getId());
            destination.setConcert(source.getConcert().getId());
            return context.getDestination();
        };
    }

    public TicketResponse ticketToTicketResponse(Ticket ticket) {
        return mapper.map(ticket, TicketResponse.class);
    }

    public Ticket ticketRequestToTicket(TicketRequest request) {
        return mapper.map(request, Ticket.class);
    }

    public Ticket ticketUpdateToTicket(TicketUpdate update) {
        return mapper.map(update, Ticket.class);
    }

    public List<TicketResponse> ticketToTicketResponseList(List<Ticket> ticket) {
        return ticket
                .stream()
                .map(this::ticketToTicketResponse)
                .collect(Collectors.toList());
    }

    public Page<TicketResponse> ticketToTicketResponsePage(Page<Ticket> ticketPage) {
        return ticketPage
                .map(this::ticketToTicketResponse);
    }
}
