package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.TicketMapper;
import com.cvetkov.fedor.laboratoryWork.repository.TicketRepository;
import com.cvetkov.fedor.laboratoryWork.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public Page<TicketResponse> getAllPage(Pageable pageable) {
        return ticketMapper.ticketToTicketResponsePage(ticketRepository.findAll(pageable));
    }

    @Override
    public List<TicketResponse> getAllList() {
        return ticketMapper.ticketToTicketResponseList(ticketRepository.findAll());
    }

    @Override
    public TicketResponse findById(Long id) {
        return ticketMapper
                .ticketToTicketResponse(ticketRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Ticket with id " + id + " not found")));
    }

    @Override
    public void save(TicketRequest ticketRequest) {
        ticketRepository.save(ticketMapper.ticketRequestToTicket(ticketRequest));
    }

    @Override
    public void update(TicketUpdate ticketUpdate) {
        ticketRepository.save(ticketMapper.ticketUpdateToTicket(ticketUpdate));
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
