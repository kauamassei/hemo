package dev.kauamassei.hemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "tb_cadastro_funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column (name = "password")
    private String password;


}
