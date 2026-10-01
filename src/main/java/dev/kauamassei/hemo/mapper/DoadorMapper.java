package dev.kauamassei.hemo.mapper;

import dev.kauamassei.hemo.dto.DoadorDTO;
import dev.kauamassei.hemo.model.DoadorModel;

public class DoadorMapper {

    public DoadorModel map(DoadorDTO doadorDTO) {
        DoadorModel doadorModel = new DoadorModel();
        doadorModel.setId(doadorDTO.getId());
        doadorModel.setCpf(doadorDTO.getCpf());
        doadorModel.setTelefone(doadorDTO.getTelefone());
        doadorModel.setDataNascimento(doadorDTO.getDataNascimento());
        doadorModel.setTipoSanguineo(doadorDTO.getTipoSanguineo());

        return doadorModel;
    }

    public DoadorDTO map(DoadorModel doadorModel) {
        DoadorDTO doadorDTO = new DoadorDTO();
        doadorDTO.setId(doadorModel.getId());
        doadorDTO.setCpf(doadorModel.getCpf());
        doadorDTO.setTelefone(doadorModel.getTelefone());
        doadorDTO.setDataNascimento(doadorModel.getDataNascimento());
        doadorDTO.setTipoSanguineo(doadorModel.getTipoSanguineo());

        return doadorDTO;
    }
}
