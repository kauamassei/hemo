package dev.kauamassei.hemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoadorDTO {

    private Long id;
    private String cpf;
    private String telefone;
    private String tipoSanguineo;
    private LocalDate dataNascimento;

}
