package dev.kauamassei.hemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacoesDTO {

    private Long id;
    private LocalDateTime dataDoacao;
    private int volumeSanguineo;
    private String pressaoArterial;
    private BigDecimal hemoglobina;
    private boolean aptidao;

}
