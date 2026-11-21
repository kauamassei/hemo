
package dev.kauamassei.hemo.mapper;

import dev.kauamassei.hemo.dto.AgendaDTO;
import dev.kauamassei.hemo.model.AgendaModel;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    public AgendaModel map(AgendaDTO agendaDTO) {
        AgendaModel agendaModel = new AgendaModel();
        agendaModel.setId(agendaDTO.getId());
        agendaModel.setDataAgenda(agendaDTO.getDataAgenda());
        agendaModel.setStatus(agendaDTO.getStatus());

        return agendaModel;
    }

    public AgendaDTO map(AgendaModel agendaModel) {
        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setId(agendaModel.getId());
        agendaDTO.setDataAgenda(agendaModel.getDataAgenda());
        agendaDTO.setStatus(agendaModel.getStatus());

        if (agendaModel.getDoador() != null) {
            agendaDTO.setDoadorId(agendaModel.getDoador().getId());
            agendaDTO.setNomeDoador(agendaModel.getDoador().getNome());
        }

        if (agendaModel.getUnidadeColeta() != null) {
            agendaDTO.setUnidadeId(agendaModel.getUnidadeColeta().getId());
            agendaDTO.setNomeUnidade(agendaModel.getUnidadeColeta().getNome());
        }

        return agendaDTO;
    }
}
