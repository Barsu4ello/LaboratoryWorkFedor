package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.AudioRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AudioResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.test.context.DynamicPropertyRegistry;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor
public class AudioTestController {

    private final AudioController audioController;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:latest");

    @BeforeAll
    public static void setUp(){
        container.withReuse(true);
        container.withInitScript("src/main/resources/db/changelog/v1.0/create_tables.sql");
        container.withInitScript("src/main/resources/db/changelog/v1.0/insert_data.sql");
        container.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.driver-class-name", container::getDriverClassName);
    }

    @Test
    public void testCreateUser(){
        AudioRequest request = new AudioRequest();
        request.setTitle("Test Title");
        request.setAuthorId(1L);
        audioController.addAudio(request);
        List<AudioResponse> response = audioController.getAllAudios();

        assertTrue(response.stream()
                .map(AudioResponse::getTitle)
                .collect(Collectors.toList())
                .contains("Test Title"));
    }

    @AfterAll
    public static void tearDown(){
        container.stop();

    }
}
