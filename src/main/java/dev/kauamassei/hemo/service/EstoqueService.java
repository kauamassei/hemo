package dev.kauamassei.hemo.service;

import dev.kauamassei.hemo.dto.EstoqueDTO;
import dev.kauamassei.hemo.mapper.EstoqueMapper;
import dev.kauamassei.hemo.model.EstoqueModel;
import dev.kauamassei.hemo.model.UnidadeColetaModel;
import dev.kauamassei.hemo.repository.EstoqueRepository;
import dev.kauamassei.hemo.repository.UnidadeColetaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;
    private final UnidadeColetaRepository unidadeColetaRepository;

    public EstoqueService(
            EstoqueRepository estoqueRepository,
            EstoqueMapper estoqueMapper,
            UnidadeColetaRepository unidadeColetaRepository) {
        this.estoqueRepository = estoqueRepository;
        this.estoqueMapper = estoqueMapper;
        this.unidadeColetaRepository = unidadeColetaRepository;
    }

    // Criar estoque
    public EstoqueDTO criarEstoque(
            Long idUnidade,
            EstoqueDTO estoqueDTO) {

        Optional<UnidadeColetaModel> unidadeOptional =
                unidadeColetaRepository.findById(idUnidade);

        if (unidadeOptional.isEmpty()) {
            return null;
        }

        if (!estoqueValido(estoqueDTO)) {
            return null;
        }

        EstoqueModel estoque = estoqueMapper.map(estoqueDTO);

        // Garante que seja um novo registro
        estoque.setId(null);

        // Associa o estoque à unidade de coleta
        estoque.setUnidadeColeta(unidadeOptional.get());

        EstoqueModel estoqueSalvo =
                estoqueRepository.save(estoque);

        return estoqueMapper.map(estoqueSalvo);
    }

    // Buscar todos os estoques
    public List<EstoqueDTO> buscarEstoques() {
        List<EstoqueModel> estoques =
                estoqueRepository.findAll();

        return estoques.stream()
                .map(estoqueMapper::map)
                .collect(Collectors.toList());
    }

    // Buscar estoque por ID
    public EstoqueDTO buscarEstoquePorId(Long id) {
        Optional<EstoqueModel> estoqueOptional =
                estoqueRepository.findById(id);

        return estoqueOptional
                .map(estoqueMapper::map)
                .orElse(null);
    }

    // Alterar estoque por ID
    public EstoqueDTO alterarEstoquePorId(
            Long id,
            EstoqueDTO estoqueDTO) {

        Optional<EstoqueModel> estoqueOptional =
                estoqueRepository.findById(id);

        if (estoqueOptional.isEmpty()) {
            return null;
        }

        if (!estoqueValido(estoqueDTO)) {
            return null;
        }

        EstoqueModel estoqueExistente = estoqueOptional.get();

        estoqueExistente.setTipoSanguineo(
                estoqueDTO.getTipoSanguineo());

        estoqueExistente.setQuantidadeMl(
                estoqueDTO.getQuantidadeMl());

        estoqueExistente.setCapacidadeMl(
                estoqueDTO.getCapacidadeMl());

        EstoqueModel estoqueSalvo =
                estoqueRepository.save(estoqueExistente);

        return estoqueMapper.map(estoqueSalvo);
    }

    // Deletar estoque por ID
    public boolean deletarEstoque(Long id) {
        Optional<EstoqueModel> estoqueOptional =
                estoqueRepository.findById(id);

        if (estoqueOptional.isEmpty()) {
            return false;
        }

        estoqueRepository.delete(estoqueOptional.get());

        return true;
    }

    // Validar quantidade e capacidade
    private boolean estoqueValido(EstoqueDTO estoqueDTO) {

        BigDecimal quantidade = estoqueDTO.getQuantidadeMl();
        BigDecimal capacidade = estoqueDTO.getCapacidadeMl();

        if (quantidade == null || capacidade == null) {
            return false;
        }

        if (quantidade.compareTo(BigDecimal.ZERO) < 0
                || capacidade.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return quantidade.compareTo(capacidade) <= 0;
    }
}
