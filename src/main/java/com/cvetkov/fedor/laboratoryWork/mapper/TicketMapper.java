package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
import com.cvetkov.fedor.laboratoryWork.model.Subscription;
import com.cvetkov.fedor.laboratoryWork.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final ModelMapper mapper;

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
