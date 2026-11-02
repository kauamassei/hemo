package dev.kauamassei.hemo.controller;

import dev.kauamassei.hemo.dto.UnidadeColetaDTO;
import dev.kauamassei.hemo.service.UnidadeColetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidade-coleta")
public class UnidadeColetaController {

    private final UnidadeColetaService unidadeColetaService;

    public UnidadeColetaController(UnidadeColetaService unidadeColetaService) {
        this.unidadeColetaService = unidadeColetaService;
    }

    // Criar unidade de coleta
    @PostMapping("/criar")
    public ResponseEntity<String> criarUnidadeColeta(
            @RequestBody UnidadeColetaDTO unidadeColetaDTO) {

        UnidadeColetaDTO unidadeCriada =
                unidadeColetaService.criarUnidadeColeta(unidadeColetaDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Unidade de coleta criada com sucesso!");
    }

    // Buscar todas as unidades de coleta
    @GetMapping("/buscar")
    public ResponseEntity<List<UnidadeColetaDTO>> buscarUnidadesColeta() {
        List<UnidadeColetaDTO> unidades =
                unidadeColetaService.buscarUnidadesColeta();

        return ResponseEntity.ok(unidades);
    }

    // Buscar unidade de coleta por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarUnidadeColetaPorId(
            @PathVariable Long id) {

        UnidadeColetaDTO unidade =
                unidadeColetaService.buscarUnidadeColetaPorId(id);

        if (unidade != null) {
            return ResponseEntity.ok(unidade);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unidade de coleta não encontrada.");
        }
    }

    // Alterar unidade de coleta por ID
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarUnidadeColetaPorId(
            @PathVariable Long id,
            @RequestBody UnidadeColetaDTO unidadeAtualizada) {

        UnidadeColetaDTO unidade =
                unidadeColetaService.alterarUnidadeColetaPorId(
                        id, unidadeAtualizada);

        if (unidade != null) {
            return ResponseEntity.ok(unidade);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unidade de coleta não encontrada.");
        }
    }

    // Deletar unidade de coleta por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarUnidadeColeta(
            @PathVariable Long id) {

        boolean unidadeDeletada =
                unidadeColetaService.deletarUnidadeColeta(id);

        if (unidadeDeletada) {
            return ResponseEntity.ok(
                    "Unidade de coleta com ID " + id
                            + " foi deletada com sucesso!"
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unidade de coleta com ID " + id
                            + " não foi encontrada.");
        }
    }
}
