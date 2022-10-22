package com.cvetkov.fedor.laboratoryWork.mapper;

import com.cvetkov.fedor.laboratoryWork.dto.request.AudioRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.AudioResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.AudioUpdate;
import com.cvetkov.fedor.laboratoryWork.model.Audio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AudioMapper {

    private final ModelMapper mapper;

    public AudioResponse audioToAudioResponse(Audio audio) {
        return mapper.map(audio, AudioResponse.class);
    }

    public Audio audioRequestToAudio(AudioRequest request) {
        return mapper.map(request, Audio.class);
    }

    public Audio audioUpdateToAudio(AudioUpdate update) {
        return mapper.map(update, Audio.class);
    }

    public List<AudioResponse> audioToAudioResponseList(List<Audio> audio) {
        return audio
                .stream()
                .map(this::audioToAudioResponse)
                .collect(Collectors.toList());
    }

    public Page<AudioResponse>  audioToAudioResponsePage(Page<Audio> audioPage) {
        return audioPage
                .map(this::audioToAudioResponse);
    }
}
