package com.cvetkov.fedor.laboratoryWork.service;

import com.cvetkov.fedor.laboratoryWork.dto.request.AuthorRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AuthorResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AuthorUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    Page<AuthorResponse> getAllPage(Pageable pageable);

    List<AuthorResponse> getAllList();

    AuthorResponse findById(Long id);

    void save(AuthorRequest authorRequest);

    void update(AuthorUpdate authorUpdate);

    void deleteById(Long id);
}
