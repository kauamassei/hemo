package dev.kauamassei.hemo.service;

import dev.kauamassei.hemo.dto.AgendaDTO;
import dev.kauamassei.hemo.dto.StatusAgendamento;
import dev.kauamassei.hemo.mapper.AgendaMapper;
import dev.kauamassei.hemo.model.AgendaModel;
import dev.kauamassei.hemo.repository.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;

    public AgendaService(
            AgendaRepository agendaRepository,
            AgendaMapper agendaMapper) {
        this.agendaRepository = agendaRepository;
        this.agendaMapper = agendaMapper;
    }

    // Buscar todos os agendamentos
    public List<AgendaDTO> buscarAgendamentos() {
        List<AgendaModel> agendas = agendaRepository.findAll();

        return agendas.stream()
                .map(agendaMapper::map)
                .collect(Collectors.toList());
    }

    // Buscar agendamento por ID
    public AgendaDTO buscarAgendamentoPorId(Long id) {
        Optional<AgendaModel> agendaOptional =
                agendaRepository.findById(id);

        return agendaOptional
                .map(agendaMapper::map)
                .orElse(null);
    }

    // Atualizar agendamento por ID
    public AgendaDTO alterarAgendamentoPorId(
            Long id,
            AgendaDTO agendaDTO) {

        Optional<AgendaModel> agendaOptional =
                agendaRepository.findById(id);

        if (agendaOptional.isPresent()) {
            AgendaModel agendaAtualizada =
                    agendaMapper.map(agendaDTO);

            AgendaModel agendaExistente = agendaOptional.get();

            agendaExistente.setDataAgenda(
                    agendaAtualizada.getDataAgenda());

            AgendaModel agendaSalva =
                    agendaRepository.save(agendaExistente);

            return agendaMapper.map(agendaSalva);
        }

        return null;
    }

    // Cancelar agendamento
    public boolean cancelarAgendamento(Long id) {
        Optional<AgendaModel> agendaOptional =
                agendaRepository.findById(id);

        if (agendaOptional.isEmpty()) {
            return false;
        }

        AgendaModel agenda = agendaOptional.get();

        if (agenda.getStatus() == StatusAgendamento.CANCELADO
                || agenda.getStatus() == StatusAgendamento.REALIZADO) {
            return false;
        }

        agenda.setStatus(StatusAgendamento.CANCELADO);

        agendaRepository.save(agenda);

        return true;
    }
}
