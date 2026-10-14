package dev.kauamassei.hemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {

    private Long id;
    private LocalDateTime dataAgenda;
    private StatusAgendamento status;

}
