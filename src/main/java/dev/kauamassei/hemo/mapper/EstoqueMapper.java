package dev.kauamassei.hemo.mapper;

import dev.kauamassei.hemo.dto.EstoqueDTO;
import dev.kauamassei.hemo.model.EstoqueModel;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public EstoqueModel map(EstoqueDTO estoqueDTO) {
        EstoqueModel estoqueModel = new EstoqueModel();
        estoqueModel.setId(estoqueDTO.getId());
        estoqueModel.setTipoSanguineo(estoqueDTO.getTipoSanguineo());
        estoqueModel.setQuantidadeMl(estoqueDTO.getQuantidadeMl());
        estoqueModel.setCapacidadeMl(estoqueDTO.getCapacidadeMl());

        return estoqueModel;
    }

    public EstoqueDTO map(EstoqueModel estoqueModel) {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setId(estoqueModel.getId());
        estoqueDTO.setTipoSanguineo(estoqueModel.getTipoSanguineo());
        estoqueDTO.setQuantidadeMl(estoqueModel.getQuantidadeMl());
        estoqueDTO.setCapacidadeMl(estoqueModel.getCapacidadeMl());

        return estoqueDTO;
    }
}
