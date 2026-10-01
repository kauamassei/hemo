package dev.kauamassei.hemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_cadastro_doador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (unique = true)
    private String cpf;

    @Column (name = "telefone")
    private String telefone;

    @Column (name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column (name = "tipo_sanguineo")
    private String tipoSanguineo;



}
