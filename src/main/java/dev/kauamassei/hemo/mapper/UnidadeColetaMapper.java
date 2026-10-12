package dev.kauamassei.hemo.mapper;

import dev.kauamassei.hemo.dto.UnidadeColetaDTO;
import dev.kauamassei.hemo.model.UnidadeColetaModel;
import org.springframework.stereotype.Component;

@Component
public class UnidadeColetaMapper {

    public UnidadeColetaModel map(UnidadeColetaDTO unidadeColetaDTO) {
        UnidadeColetaModel unidadeModel = new UnidadeColetaModel();
        unidadeModel.setId(unidadeColetaDTO.getId());
        unidadeModel.setNome(unidadeColetaDTO.getNome());
        unidadeModel.setCnpj(unidadeColetaDTO.getCnpj());
        unidadeModel.setTelefone(unidadeColetaDTO.getTelefone());
        unidadeModel.setEndereco(unidadeColetaDTO.getEndereco());
        unidadeModel.setCidade(unidadeColetaDTO.getCidade());
        unidadeModel.setEstado(unidadeColetaDTO.getEstado());

        return unidadeModel;
    }

    public UnidadeColetaDTO map(UnidadeColetaModel unidadeColetaModel) {
        UnidadeColetaDTO unidadeDTO = new UnidadeColetaDTO();
        unidadeDTO.setId(unidadeColetaModel.getId());
        unidadeDTO.setNome(unidadeColetaModel.getNome());
        unidadeDTO.setCnpj(unidadeColetaModel.getCnpj());
        unidadeDTO.setTelefone(unidadeColetaModel.getTelefone());
        unidadeDTO.setEndereco(unidadeColetaModel.getEndereco());
        unidadeDTO.setCidade(unidadeColetaModel.getCidade());
        unidadeDTO.setEstado(unidadeColetaModel.getEstado());

        return unidadeDTO;

    }


}
