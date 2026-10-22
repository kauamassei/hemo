package dev.kauamassei.hemo.service;

import dev.kauamassei.hemo.dto.DoacoesDTO;
import dev.kauamassei.hemo.dto.StatusAgendamento;
import dev.kauamassei.hemo.mapper.DoacoesMapper;
import dev.kauamassei.hemo.model.AgendaModel;
import dev.kauamassei.hemo.model.DoacoesModel;
import dev.kauamassei.hemo.repository.AgendaRepository;
import dev.kauamassei.hemo.repository.DoacoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoacoesService {

    private final DoacoesRepository doacoesRepository;
    private final DoacoesMapper doacoesMapper;
    private final AgendaRepository agendaRepository;

    public DoacoesService(
            DoacoesRepository doacoesRepository,
            DoacoesMapper doacoesMapper,
            AgendaRepository agendaRepository) {
        this.doacoesRepository = doacoesRepository;
        this.doacoesMapper = doacoesMapper;
        this.agendaRepository = agendaRepository;
    }

    // Cadastrar doação
    public DoacoesDTO criarDoacao(
            Long idAgendamento,
            DoacoesDTO doacoesDTO) {

        Optional<AgendaModel> agendaOptional =
                agendaRepository.findById(idAgendamento);

        if (agendaOptional.isEmpty()) {
            return null;
        }

        AgendaModel agenda = agendaOptional.get();

        if (agenda.getStatus() != StatusAgendamento.AGENDADO
                || agenda.getDoacao() != null) {
            return null;
        }

        DoacoesModel doacao = doacoesMapper.map(doacoesDTO);

        doacao.setId(null);
        doacao.setAgendamento(agenda);

        DoacoesModel doacaoSalva =
                doacoesRepository.save(doacao);

        agenda.setStatus(StatusAgendamento.REALIZADO);
        agendaRepository.save(agenda);

        return doacoesMapper.map(doacaoSalva);
    }

    // Buscar todas as doações
    public List<DoacoesDTO> buscarDoacoes() {
        List<DoacoesModel> doacoes =
                doacoesRepository.findAll();

        return doacoes.stream()
                .map(doacoesMapper::map)
                .collect(Collectors.toList());
    }

    // Buscar doação por ID
    public DoacoesDTO buscarDoacaoPorId(Long id) {
        Optional<DoacoesModel> doacaoOptional =
                doacoesRepository.findById(id);

        return doacaoOptional
                .map(doacoesMapper::map)
                .orElse(null);
    }

    // Excluir doação por ID
    public boolean deletarDoacao(Long id) {
        Optional<DoacoesModel> doacaoOptional =
                doacoesRepository.findById(id);

        if (doacaoOptional.isEmpty()) {
            return false;
        }

        doacoesRepository.delete(doacaoOptional.get());

        return true;
    }
}
