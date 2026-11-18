package dev.kauamassei.hemo.mapper;

import dev.kauamassei.hemo.dto.DoadorDTO;
import dev.kauamassei.hemo.model.DoadorModel;
import org.springframework.stereotype.Component;

@Component
public class DoadorMapper {

    public DoadorModel map(DoadorDTO doadorDTO) {
        DoadorModel doadorModel = new DoadorModel();
        doadorModel.setId(doadorDTO.getId());
        doadorModel.setNome(doadorDTO.getNome());
        doadorModel.setCpf(doadorDTO.getCpf());
        doadorModel.setTelefone(doadorDTO.getTelefone());
        doadorModel.setDataNascimento(doadorDTO.getDataNascimento());
        doadorModel.setTipoSanguineo(doadorDTO.getTipoSanguineo());
        doadorModel.setEmail(doadorDTO.getEmail());
        doadorModel.setEndereco(doadorDTO.getEndereco());
        doadorModel.setSexo(doadorDTO.getSexo());
        doadorModel.setCep(doadorDTO.getCep());




        return doadorModel;
    }

    public DoadorDTO map(DoadorModel doadorModel) {
        DoadorDTO doadorDTO = new DoadorDTO();
        doadorDTO.setId(doadorModel.getId());
        doadorDTO.setNome(doadorModel.getNome());
        doadorDTO.setCpf(doadorModel.getCpf());
        doadorDTO.setTelefone(doadorModel.getTelefone());
        doadorDTO.setDataNascimento(doadorModel.getDataNascimento());
        doadorDTO.setTipoSanguineo(doadorModel.getTipoSanguineo());
        doadorDTO.setEmail(doadorModel.getEmail());
        doadorDTO.setEndereco(doadorModel.getEndereco());
        doadorDTO.setSexo(doadorModel.getSexo());
        doadorDTO.setCep(doadorModel.getCep());

        return doadorDTO;
    }
}
