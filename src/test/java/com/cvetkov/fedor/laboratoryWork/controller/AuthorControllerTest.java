package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.AuthorRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AuthorResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AuthorUpdate;
import com.cvetkov.fedor.laboratoryWork.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorControllerTest extends LaboratoryWorkApplicationTests {
    private final AuthorController authorController;

    @Autowired
    public AuthorControllerTest(AuthorController authorController) {
        this.authorController = authorController;
    }

    @Test
    void listWithAllAuthorsShouldBeNotEmptyAfterAddAuthor() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("test name");
        authorRequest.setDescription("test description");
        List<AuthorResponse> authors = authorController.getAllAuthors();

        Assertions.assertFalse(authors.isEmpty());
    }

    @Test
    void pageWithAllAuthorsShouldBeNotEmptyAfterAddAuthor() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("testName");
        authorRequest.setDescription("testDescription");
        authorController.addAuthor(authorRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<AuthorResponse> authors = authorController.getAllAuthors(sourcePageable);

        Assertions.assertTrue(authors.getTotalPages() > 0);
        Assertions.assertFalse(authors.isEmpty());
    }

    @Test
    void listSizeAuthorsAfterAddAuthorShouldBeContainsThisAuthor() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("test name");
        authorRequest.setDescription("test description");
        authorController.addAuthor(authorRequest);
        List<AuthorResponse> authors = authorController.getAllAuthors();

        Assertions.assertTrue(authors.stream()
                .map(AuthorResponse::getName)
                .collect(Collectors.toList())
                .contains("test name"));

        Assertions.assertTrue(authors.stream()
                .map(AuthorResponse::getDescription)
                .collect(Collectors.toList())
                .contains("test description"));
    }

    @Test
    void dbShouldBeReturnAuthorWithIdOne() {
        AuthorResponse authorResponse = authorController.getAuthorById(1L);

        Assertions.assertEquals(1L, authorResponse.getId());
        Assertions.assertEquals("Vlad", authorResponse.getName());
        Assertions.assertEquals("Vlad description", authorResponse.getDescription());
    }

    @Test
    void authorShouldBeChangedAfterUpdate() {
        AuthorUpdate authorUpdate = new AuthorUpdate();
        authorUpdate.setId(1L);
        authorUpdate.setName("update");
        authorUpdate.setDescription("update description");
        authorController.updateAuthor(authorUpdate);
        AuthorResponse authorResponse = authorController.getAuthorById(1L);


        Assertions.assertEquals("update", authorResponse.getName());
        Assertions.assertEquals("update description", authorResponse.getDescription());
    }

    @Test
    void listAuthorsInDbShouldBeLessOneAfterDeleteAuthor() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("test");
        authorRequest.setDescription("test description");
        authorController.addAuthor(authorRequest);

        List<AuthorResponse> responses = authorController.getAllAuthors();
        int size = responses.size();
        authorController.deleteAuthor(1L);
        int sizeAfterRemove = authorController.getAllAuthors().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> authorController.getAuthorById(10L));
    }
}
