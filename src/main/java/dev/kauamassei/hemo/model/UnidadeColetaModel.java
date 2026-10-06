package dev.kauamassei.hemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "tb_cadastro_funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeColetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column (name = "cnpj", unique = true)
    private String cnpj;

    @Column (name = "telefone")
    private String telefone;

    @Column (name = "endereco")
    private String endereco;

    @Column (name = "cidade")
    private String cidade;

    @Column (name = "estado")
    private String estado;

    @OneToMany(mappedBy = "unidadeColeta")
    private List<AgendaModel> agendamentos;

    @OneToMany(mappedBy = "unidadeColeta")
    private List<EstoqueModel> estoques;



}
