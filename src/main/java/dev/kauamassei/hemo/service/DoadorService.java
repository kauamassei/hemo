package dev.kauamassei.hemo.service;

import dev.kauamassei.hemo.dto.AgendaDTO;
import dev.kauamassei.hemo.dto.DoacoesDTO;
import dev.kauamassei.hemo.dto.DoadorDTO;
import dev.kauamassei.hemo.dto.StatusAgendamento;
import dev.kauamassei.hemo.mapper.AgendaMapper;
import dev.kauamassei.hemo.mapper.DoacoesMapper;
import dev.kauamassei.hemo.mapper.DoadorMapper;
import dev.kauamassei.hemo.model.AgendaModel;
import dev.kauamassei.hemo.model.DoadorModel;
import dev.kauamassei.hemo.model.UnidadeColetaModel;
import dev.kauamassei.hemo.repository.AgendaRepository;
import dev.kauamassei.hemo.repository.DoadorRepository;
import dev.kauamassei.hemo.repository.UnidadeColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Objects;

@Service
public class DoadorService {

    private AgendaRepository agendaRepository;
    private UnidadeColetaRepository unidadeColetaRepository;
    private AgendaMapper agendaMapper;
    private DoacoesMapper doacoesMapper;
    private DoadorRepository doadorRepository;
    private DoadorMapper doadorMapper;

    public DoadorService(AgendaRepository agendaRepository, UnidadeColetaRepository unidadeColetaRepository, AgendaMapper agendaMapper, DoacoesMapper doacoesMapper, DoadorRepository doadorRepository, DoadorMapper doadorMapper) {
        this.agendaRepository = agendaRepository;
        this.unidadeColetaRepository = unidadeColetaRepository;
        this.agendaMapper = agendaMapper;
        this.doacoesMapper = doacoesMapper;
        this.doadorRepository = doadorRepository;
        this.doadorMapper = doadorMapper;
    }

    //criar user
    public DoadorDTO criarDoador(DoadorDTO doadorDTO) {
        DoadorModel doador = doadorMapper.map(doadorDTO);
        doador = doadorRepository.save(doador);
        return doadorMapper.map(doador);
    }

    //get user
    public List<DoadorDTO> buscarDoador() {
        List<DoadorModel> doadores = doadorRepository.findAll();
        return doadores.stream()
                .map(doadorMapper::map)
                .collect(Collectors.toList());
    }

    //buscar por id
    public DoadorDTO buscarDoadorPorId(Long id) {
        Optional<DoadorModel> doadorPorId = doadorRepository.findById(id);
        return doadorPorId.map(doadorMapper::map).orElse(null);
    }

    //put user
    public DoadorDTO alterarDoadorPorId(Long id, DoadorDTO doadorDTO) {
        Optional<DoadorModel> doadorPorId = doadorRepository.findById(id);
        if (doadorPorId.isPresent()) {
            DoadorModel doadorAtualizado = doadorMapper.map(doadorDTO);
            doadorAtualizado.setId(id);
            DoadorModel doadorSalvo = doadorRepository.save(doadorAtualizado);
            return doadorMapper.map(doadorSalvo);
        }

        return null;
    }

    //delete user
    public void deletarDoador(Long id) {
        doadorRepository.deleteById(id);
    }


    //consultar doacoes
    public List<DoacoesDTO> consultarDoacoes(Long idDoador) {

        Optional<DoadorModel> doadorOptional =
                doadorRepository.findById(idDoador);

        if (doadorOptional.isEmpty()) {
            return null;
        }

        DoadorModel doador = doadorOptional.get();

        return doador.getAgendamentos().stream()
                .map(AgendaModel::getDoacao)
                .filter(Objects::nonNull)
                .map(doacoesMapper::map)
                .collect(Collectors.toList());
    }

    //agendar doacao
    public AgendaDTO agendarDoacao(
            Long idDoador,
            Long idUnidade,
            AgendaDTO agendaDTO) {

        Optional<DoadorModel> doadorOptional =
                doadorRepository.findById(idDoador);

        if (doadorOptional.isEmpty()) {
            return null;
        }

        Optional<UnidadeColetaModel> unidadeOptional =
                unidadeColetaRepository.findById(idUnidade);

        if (unidadeOptional.isEmpty()) {
            return null;
        }

        AgendaModel agenda = agendaMapper.map(agendaDTO);

        // Garante que seja um novo agendamento
        agenda.setId(null);

        // Define os relacionamentos
        agenda.setDoador(doadorOptional.get());
        agenda.setUnidadeColeta(unidadeOptional.get());

        // Define o status inicial
        agenda.setStatus(StatusAgendamento.AGENDADO);

        AgendaModel agendaSalva = agendaRepository.save(agenda);

        return agendaMapper.map(agendaSalva);
    }


    //cancelar agendamento
    public boolean cancelarAgendamento(Long idDoador, Long idAgendamento) {

        Optional<AgendaModel> agendaOptional =
                agendaRepository.findById(idAgendamento);

        if (agendaOptional.isEmpty()) {
            return false;
        }

        AgendaModel agenda = agendaOptional.get();

        if (!agenda.getDoador().getId().equals(idDoador)) {
            return false;
        }

        if (agenda.getStatus() == StatusAgendamento.CANCELADO) {
            return false;
        }

        agenda.setStatus(StatusAgendamento.CANCELADO);

        agendaRepository.save(agenda);

        return true;
    }

}
