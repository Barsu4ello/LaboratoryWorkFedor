package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.AudioRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.AuthorRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AudioResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.AuthorResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AudioUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.AuthorUpdate;
import com.cvetkov.fedor.laboratoryWork.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public Page<AuthorResponse> getAllAuthors(@PageableDefault(size = 5) Pageable pageable) {
        return authorService.getAllPage(pageable);
    }

    @GetMapping("/all-author")
    public List<AuthorResponse> getAllAuthors() {
        return authorService.getAllList();
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping
    public void addAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
        authorService.save(authorRequest);
    }

    @PutMapping
    public void updateAuthor(@Valid @RequestBody AuthorUpdate authorUpdate) {
        authorService.update(authorUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
