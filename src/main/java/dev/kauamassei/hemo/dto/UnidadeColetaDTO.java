package dev.kauamassei.hemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeColetaDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;


}
