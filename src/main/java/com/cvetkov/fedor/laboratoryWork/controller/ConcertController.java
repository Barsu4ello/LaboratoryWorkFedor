package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.ConcertRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.ConcertResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.ConcertUpdate;
import com.cvetkov.fedor.laboratoryWork.service.ConcertService;
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
@RequestMapping(value = "/api/v1/concert")
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<ConcertResponse> getAllConcerts(@PageableDefault(size = 5) Pageable pageable) {
        return concertService.getAllPage(pageable);
    }

    @GetMapping("/all-concert")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<ConcertResponse> getAllConcerts() {
        return concertService.getAllList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ConcertResponse getConcertById(@PathVariable Long id) {
        return concertService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addConcert(@Valid @RequestBody ConcertRequest concertRequest) {
        concertService.save(concertRequest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateConcert(@Valid @RequestBody ConcertUpdate concertUpdate) {
        concertService.update(concertUpdate);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteConcert(@PathVariable Long id) {
        concertService.deleteById(id);
    }
}
