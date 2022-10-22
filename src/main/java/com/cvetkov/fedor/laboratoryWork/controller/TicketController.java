package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
import com.cvetkov.fedor.laboratoryWork.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public Page<TicketResponse> getAllTickets(@PageableDefault(size = 5) Pageable pageable) {
        return ticketService.getAllPage(pageable);
    }

    @GetMapping("/all-ticket")
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllList();
    }

    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PostMapping
    public void addTicket(@Valid @RequestBody TicketRequest ticketRequest) {
        ticketService.save(ticketRequest);
    }

    @PutMapping
    public void updateTicket(@Valid @RequestBody TicketUpdate ticketUpdate) {
        ticketService.update(ticketUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
    }
}
