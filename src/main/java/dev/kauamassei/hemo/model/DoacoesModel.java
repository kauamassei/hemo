package dev.kauamassei.hemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_doacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "data_doacao")
    private LocalDateTime dataDoacao;

    @Column (name = "volume_sanguineo")
    private int volumeSanguineo;

    @Column (name = "pressao_arterial")
    private String pressaoArterial;

    @Column (name = "temperatura")
    private BigDecimal temperatura;

    @Column (name = "hemoglobina")
    private BigDecimal hemoglobina;

    @Column (name = "aptidao")
    private boolean aptidao;


}
