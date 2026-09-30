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

    //cpf
    @Column (unique = true)
    private String cpf;

    //telefone
    @Column (name = "telefone")
    private String telefone;

    //data nasc
    @Column (name = "data_nascimento")
    private LocalDate dataNascimento;

    //tipo sanguineo
    @Column (name = "tipo_sanguineo")
    private String tipoSanguineo;



}
