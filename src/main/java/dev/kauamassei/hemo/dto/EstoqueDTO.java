package dev.kauamassei.hemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO {

    private Long id;
    private String tipoSanguineo;
    private BigDecimal quantidadeMl;
    private BigDecimal capacidadeMl;

}
