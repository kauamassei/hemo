package dev.kauamassei.hemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table (name = "tb_estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "tipo_sanguineo")
    private String tipoSanguineo;

    @Column (name = "quantidade_ml")
    private BigDecimal quantidadeMl;

    @Column (name = "capacidade_ml")
    private BigDecimal capacidadeMl;

    @ManyToOne
    @JoinColumn(name = "unidade_coleta_id")
    private UnidadeColetaModel unidadeColeta;


}
