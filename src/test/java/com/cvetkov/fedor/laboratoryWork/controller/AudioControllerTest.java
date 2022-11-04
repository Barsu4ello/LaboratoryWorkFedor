package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.LaboratoryWorkApplicationTests;
import com.cvetkov.fedor.laboratoryWork.dto.request.AudioRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AudioResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AudioUpdate;
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


public class AudioControllerTest extends LaboratoryWorkApplicationTests {

    private final AudioController audioController;

    @Autowired
    public AudioControllerTest(AudioController audioController) {
        this.audioController = audioController;
    }

    @Test
    void listWithAllAudiosShouldBeNotEmptyAfterAddAudio() {
        AudioRequest audioRequest = new AudioRequest();
        audioRequest.setTitle("test");
        audioRequest.setAuthorId(1L);
        List<AudioResponse> audios = audioController.getAllAudios();

        Assertions.assertFalse(audios.isEmpty());
    }

    @Test
    void pageWithAllAudioShouldBeNotEmptyAfterAddAudio() {
        AudioRequest audioRequest = new AudioRequest();
        audioRequest.setTitle("test");
        audioRequest.setAuthorId(1L);
        audioController.addAudio(audioRequest);

        Pageable sourcePageable = mock(Pageable.class);
        when(sourcePageable.getPageNumber()).thenReturn(1);
        when(sourcePageable.getPageSize()).thenReturn(2);

        Page<AudioResponse> audios = audioController.getAllAudios(sourcePageable);

        Assertions.assertTrue(audios.getTotalPages() > 0);
        Assertions.assertFalse(audios.isEmpty());
    }

    @Test
    void listSizeAudiosAfterAddAudioShouldBeContainsThisAudio() {
        AudioRequest audioRequest = new AudioRequest();
        audioRequest.setTitle("test");
        audioRequest.setAuthorId(1L);
        audioController.addAudio(audioRequest);
        List<AudioResponse> audioResponse = audioController.getAllAudios();

        Assertions.assertTrue(audioResponse.stream()
                .map(AudioResponse::getTitle)
                .collect(Collectors.toList())
                .contains("test"));
    }

    @Test
    void dbShouldBeReturnAudioWithIdOne() {
        AudioResponse audioResponse = audioController.getAudioById(1L);

        Assertions.assertEquals(1L, audioResponse.getId());
        Assertions.assertNull(audioResponse.getAudio());
        Assertions.assertEquals("First audio", audioResponse.getTitle());
        Assertions.assertEquals(1L, audioResponse.getAuthor());
    }

    @Test
    void audioShouldBeChangedAfterUpdate() {
        AudioUpdate audioUpdate = new AudioUpdate();
        audioUpdate.setId(1L);
        audioUpdate.setTitle("update");
        audioUpdate.setAuthorId(2L);
        audioController.updateAudio(audioUpdate);
        AudioResponse audioResponse = audioController.getAudioById(1L);

        Assertions.assertNull(audioResponse.getAudio());
        Assertions.assertEquals("update", audioResponse.getTitle());
        Assertions.assertEquals(2L, audioResponse.getAuthor());
    }

    @Test
    void listAudiosInDbShouldBeLessOneAfterDeleteAudio() {
        AudioRequest audioRequest = new AudioRequest();
        audioRequest.setTitle("test");
        audioRequest.setAuthorId(1L);
        audioController.addAudio(audioRequest);

        List<AudioResponse> responses = audioController.getAllAudios();
        int size = responses.size();
        audioController.deleteAudio(1L);
        int sizeAfterRemove = audioController.getAllAudios().size();

        Assertions.assertEquals(size, sizeAfterRemove + 1);
    }

    @Test
    void shouldTrowObjectNotFoundExceptionIfIdNotFound() {
        Assertions.assertThrows(ObjectNotFoundException.class, () -> audioController.getAudioById(10L));
    }
}
