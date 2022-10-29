package com.cvetkov.fedor.laboratoryWork.service.impl;

import com.cvetkov.fedor.laboratoryWork.dto.request.AuthorRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AuthorResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AuthorUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import com.cvetkov.fedor.laboratoryWork.mapper.AuthorMapper;
import com.cvetkov.fedor.laboratoryWork.model.User;
import com.cvetkov.fedor.laboratoryWork.repository.AuthorRepository;
import com.cvetkov.fedor.laboratoryWork.repository.UserRepository;
import com.cvetkov.fedor.laboratoryWork.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final AuthorMapper authorMapper;

    @Override
    public Page<AuthorResponse> getAllPage(Pageable pageable) {
        return authorMapper.authorToAuthorResponsePage(authorRepository.findAll(pageable));
    }

    @Override
    public List<AuthorResponse> getAllList() {
        return authorMapper.authorToAuthorResponseList(authorRepository.findAll());
    }

    @Override
    public AuthorResponse findById(Long id) {
        return authorMapper
                .authorToAuthorResponse(authorRepository
                        .findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Author with id " + id + " not found")));
    }

    @Override
    public void save(AuthorRequest authorRequest) {
        authorRepository.save(authorMapper.authorRequestToAuthor(authorRequest));
    }

    @Override
    public void update(AuthorUpdate authorUpdate) {
        authorRepository.save(authorMapper.authorUpdateToAuthor(authorUpdate));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        List<User> users = userRepository.findUSersByAuthorId(id);
        users = users.stream().peek(user -> user.setAuthor(null)).collect(Collectors.toList());
        userRepository.saveAll(users);
        authorRepository.deleteById(id);
    }
}
