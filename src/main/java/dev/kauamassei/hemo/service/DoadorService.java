package dev.kauamassei.hemo.service;

import dev.kauamassei.hemo.dto.DoadorDTO;
import dev.kauamassei.hemo.mapper.DoadorMapper;
import dev.kauamassei.hemo.model.DoadorModel;
import dev.kauamassei.hemo.repository.DoadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoadorService {

    private DoadorRepository doadorRepository;
    private DoadorMapper doadorMapper;

    public DoadorService(DoadorRepository doadorRepository, DoadorMapper doadorMapper) {
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

    //agendar docao

    //cancelar agendamento

}
