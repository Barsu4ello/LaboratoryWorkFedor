package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
import com.cvetkov.fedor.laboratoryWork.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<TicketResponse> getAllTickets(@PageableDefault(size = 5) Pageable pageable) {
        return ticketService.getAllPage(pageable);
    }

    @GetMapping("/all-ticket")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addTicket(@Valid @RequestBody TicketRequest ticketRequest) {
        ticketService.save(ticketRequest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateTicket(@Valid @RequestBody TicketUpdate ticketUpdate) {
        ticketService.update(ticketUpdate);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
    }
}
