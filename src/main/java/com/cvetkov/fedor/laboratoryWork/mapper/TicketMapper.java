package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AudioResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
import com.cvetkov.fedor.laboratoryWork.model.Audio;
import com.cvetkov.fedor.laboratoryWork.model.Subscription;
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

//        mapper.createTypeMap(TicketRequest.class, Ticket.class)
//                .addMappings(m -> m.skip(Ticket::setId))
////                .addMappings(m -> m.skip(Ticket::setConcert));
//                .setPostConverter(toTicketConverter());
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

//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(TicketRequest.class, Ticket.class)
//                .addMappings(m -> m.skip(Ticket::setUser))
//                .addMappings(m -> m.skip(Ticket::setConcert))
//                .setPostConverter(toTicketConverter());
//    }

//    private Converter<TicketRequest, Ticket> toTicketConverter() {
//        return context -> {
//            TicketRequest source = context.getSource();
//            Ticket destination = context.getDestination();
////            destination.setUser(source.getUserId());
////            destination.setConcert(source.getConcertId());
//            return context.getDestination();
//        };
//    }

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
