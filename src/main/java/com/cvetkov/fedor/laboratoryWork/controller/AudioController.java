package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.AudioRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AudioResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AudioUpdate;
import com.cvetkov.fedor.laboratoryWork.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/audio")
public class AudioController {

    private final AudioService audioService;

    @GetMapping
    public Page<AudioResponse> getAllAudios(@PageableDefault(size = 5) Pageable pageable) {
        return audioService.getAllPage(pageable);
    }

    @GetMapping("/all-audio")
    public List<AudioResponse> getAllAudios() {
        return audioService.getAllList();
    }

    @GetMapping("/{id}")
    public AudioResponse getAudioById(@PathVariable Long id) {
        return audioService.findById(id);
    }

    @PostMapping
    public void addAudio(@Valid @RequestBody AudioRequest audioRequest) {
        audioService.save(audioRequest);
    }

    @PutMapping
    public void updateAudio(@Valid @RequestBody AudioUpdate audioUpdate) {
        audioService.update(audioUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteAudio(@PathVariable Long id) {
        audioService.disableById(id);
    }
}
