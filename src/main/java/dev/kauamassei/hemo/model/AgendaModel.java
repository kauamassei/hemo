package dev.kauamassei.hemo.model;

import dev.kauamassei.hemo.dto.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_agendamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "data_agenda")
    private LocalDateTime dataAgenda;

    @Enumerated(EnumType.STRING)
    @Column (name = "status")
    private StatusAgendamento status;
    
    @ManyToOne
    @JoinColumn(name = "doador_id")
    private DoadorModel doador;

    @OneToOne(mappedBy = "agendamento")
    private DoacoesModel doacao;

    @ManyToOne
    @JoinColumn(name = "unidade_coleta_id")
    private UnidadeColetaModel unidadeColeta;

}
