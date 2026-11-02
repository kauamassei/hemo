package dev.kauamassei.hemo.controller;

import dev.kauamassei.hemo.dto.EstoqueDTO;
import dev.kauamassei.hemo.service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    // Criar estoque
    @PostMapping("/criar/{idUnidade}")
    public ResponseEntity<?> criarEstoque(
            @PathVariable Long idUnidade,
            @RequestBody EstoqueDTO estoqueDTO) {

        EstoqueDTO estoqueCriado =
                estoqueService.criarEstoque(idUnidade, estoqueDTO);

        if (estoqueCriado != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Estoque criado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível criar o estoque. Verifique se a unidade existe e se as quantidades informadas são válidas.");
        }
    }

    // Buscar todos os estoques
    @GetMapping("/buscar")
    public ResponseEntity<List<EstoqueDTO>> buscarEstoques() {
        List<EstoqueDTO> estoques = estoqueService.buscarEstoques();

        return ResponseEntity.ok(estoques);
    }

    // Buscar estoque por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarEstoquePorId(@PathVariable Long id) {
        EstoqueDTO estoque = estoqueService.buscarEstoquePorId(id);

        if (estoque != null) {
            return ResponseEntity.ok(estoque);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Estoque não encontrado.");
        }
    }

    // Alterar estoque por ID
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarEstoquePorId(
            @PathVariable Long id,
            @RequestBody EstoqueDTO estoqueAtualizado) {

        EstoqueDTO estoque =
                estoqueService.alterarEstoquePorId(id, estoqueAtualizado);

        if (estoque != null) {
            return ResponseEntity.ok(estoque);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível atualizar o estoque. Verifique se ele existe e se as quantidades informadas são válidas.");
        }
    }

    // Deletar estoque por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEstoque(@PathVariable Long id) {

        boolean estoqueDeletado = estoqueService.deletarEstoque(id);

        if (estoqueDeletado) {
            return ResponseEntity.ok(
                    "Estoque com ID " + id + " foi deletado com sucesso!"
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Estoque com ID " + id + " não foi encontrado.");
        }
    }
}
