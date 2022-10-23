package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.ConcertRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.ConcertResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.ConcertUpdate;
import com.cvetkov.fedor.laboratoryWork.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/concert")
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping
    public Page<ConcertResponse> getAllConcerts(@PageableDefault(size = 5) Pageable pageable) {
        return concertService.getAllPage(pageable);
    }

    @GetMapping("/all-concert")
    public List<ConcertResponse> getAllConcerts() {
        return concertService.getAllList();
    }

    @GetMapping("/{id}")
    public ConcertResponse getConcertById(@PathVariable Long id) {
        return concertService.findById(id);
    }

    @PostMapping
    public void addConcert(@Valid @RequestBody ConcertRequest concertRequest) {
        concertService.save(concertRequest);
    }

    @PutMapping
    public void updateConcert(@Valid @RequestBody ConcertUpdate concertUpdate) {
        concertService.update(concertUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteConcert(@PathVariable Long id) {
        concertService.deleteById(id);
    }
}
