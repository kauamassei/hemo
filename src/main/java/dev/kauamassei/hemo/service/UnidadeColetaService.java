package dev.kauamassei.hemo.service;

import dev.kauamassei.hemo.dto.UnidadeColetaDTO;
import dev.kauamassei.hemo.mapper.UnidadeColetaMapper;
import dev.kauamassei.hemo.model.UnidadeColetaModel;
import dev.kauamassei.hemo.repository.UnidadeColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeColetaService {

    private final UnidadeColetaRepository unidadeColetaRepository;
    private final UnidadeColetaMapper unidadeColetaMapper;

    public UnidadeColetaService(
            UnidadeColetaRepository unidadeColetaRepository,
            UnidadeColetaMapper unidadeColetaMapper) {
        this.unidadeColetaRepository = unidadeColetaRepository;
        this.unidadeColetaMapper = unidadeColetaMapper;
    }

    // Criar unidade de coleta
    public UnidadeColetaDTO criarUnidadeColeta(
            UnidadeColetaDTO unidadeColetaDTO) {

        UnidadeColetaModel unidade =
                unidadeColetaMapper.map(unidadeColetaDTO);

        unidade.setId(null);

        UnidadeColetaModel unidadeSalva =
                unidadeColetaRepository.save(unidade);

        return unidadeColetaMapper.map(unidadeSalva);
    }

    // Buscar todas as unidades de coleta
    public List<UnidadeColetaDTO> buscarUnidadesColeta() {
        List<UnidadeColetaModel> unidades =
                unidadeColetaRepository.findAll();

        return unidades.stream()
                .map(unidadeColetaMapper::map)
                .collect(Collectors.toList());
    }

    // Buscar unidade de coleta por ID
    public UnidadeColetaDTO buscarUnidadeColetaPorId(Long id) {
        Optional<UnidadeColetaModel> unidadeOptional =
                unidadeColetaRepository.findById(id);

        return unidadeOptional
                .map(unidadeColetaMapper::map)
                .orElse(null);
    }

    // Alterar unidade de coleta por ID
    public UnidadeColetaDTO alterarUnidadeColetaPorId(
            Long id,
            UnidadeColetaDTO unidadeColetaDTO) {

        Optional<UnidadeColetaModel> unidadeOptional =
                unidadeColetaRepository.findById(id);

        if (unidadeOptional.isPresent()) {

            UnidadeColetaModel unidadeExistente =
                    unidadeOptional.get();

            UnidadeColetaModel unidadeAtualizada =
                    unidadeColetaMapper.map(unidadeColetaDTO);

            unidadeExistente.setNome(unidadeAtualizada.getNome());
            unidadeExistente.setCnpj(unidadeAtualizada.getCnpj());
            unidadeExistente.setTelefone(unidadeAtualizada.getTelefone());
            unidadeExistente.setEndereco(unidadeAtualizada.getEndereco());
            unidadeExistente.setCidade(unidadeAtualizada.getCidade());
            unidadeExistente.setEstado(unidadeAtualizada.getEstado());

            UnidadeColetaModel unidadeSalva =
                    unidadeColetaRepository.save(unidadeExistente);

            return unidadeColetaMapper.map(unidadeSalva);
        }

        return null;
    }

    // Deletar unidade de coleta por ID
    public boolean deletarUnidadeColeta(Long id) {
        Optional<UnidadeColetaModel> unidadeOptional =
                unidadeColetaRepository.findById(id);

        if (unidadeOptional.isEmpty()) {
            return false;
        }

        unidadeColetaRepository.delete(unidadeOptional.get());

        return true;
    }
}
