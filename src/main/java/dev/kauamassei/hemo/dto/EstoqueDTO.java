package dev.kauamassei.hemo.dto;

import dev.kauamassei.hemo.model.UnidadeColetaModel;
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
    private Long unidadeId;
    private String unidadeColeta;

}
