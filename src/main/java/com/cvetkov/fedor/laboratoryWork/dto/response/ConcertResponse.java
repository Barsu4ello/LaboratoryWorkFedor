package com.cvetkov.fedor.laboratoryWork.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertResponse {

    private Long id;
    private String name;
    private String performer;
    private LocalDateTime concertDate;
    private Long city;
}
