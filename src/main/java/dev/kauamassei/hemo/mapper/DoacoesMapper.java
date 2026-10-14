
package dev.kauamassei.hemo.mapper;

import dev.kauamassei.hemo.dto.DoacoesDTO;
import dev.kauamassei.hemo.model.DoacoesModel;
import org.springframework.stereotype.Component;

@Component
public class DoacoesMapper {

    public DoacoesModel map(DoacoesDTO doacoesDTO) {
        DoacoesModel doacoesModel = new DoacoesModel();
        doacoesModel.setId(doacoesDTO.getId());
        doacoesModel.setDataDoacao(doacoesDTO.getDataDoacao());
        doacoesModel.setVolumeSanguineo(doacoesDTO.getVolumeSanguineo());
        doacoesModel.setPressaoArterial(doacoesDTO.getPressaoArterial());
        doacoesModel.setHemoglobina(doacoesDTO.getHemoglobina());
        doacoesModel.setAptidao(doacoesDTO.isAptidao());

        return doacoesModel;
    }

    public DoacoesDTO map(DoacoesModel doacoesModel) {
        DoacoesDTO doacoesDTO = new DoacoesDTO();
        doacoesDTO.setId(doacoesModel.getId());
        doacoesDTO.setDataDoacao(doacoesModel.getDataDoacao());
        doacoesDTO.setVolumeSanguineo(doacoesModel.getVolumeSanguineo());
        doacoesDTO.setPressaoArterial(doacoesModel.getPressaoArterial());
        doacoesDTO.setHemoglobina(doacoesModel.getHemoglobina());
        doacoesDTO.setAptidao(doacoesModel.isAptidao());

        return doacoesDTO;
    }
}
