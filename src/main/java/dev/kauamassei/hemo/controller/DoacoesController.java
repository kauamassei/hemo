package dev.kauamassei.hemo.controller;

import dev.kauamassei.hemo.dto.DoacoesDTO;
import dev.kauamassei.hemo.service.DoacoesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacoesController {

    private final DoacoesService doacoesService;

    public DoacoesController(DoacoesService doacoesService) {
        this.doacoesService = doacoesService;
    }

    // Cadastrar doação
    @PostMapping("/criar/{idAgendamento}")
    public ResponseEntity<?> criarDoacao(
            @PathVariable Long idAgendamento,
            @RequestBody DoacoesDTO doacoesDTO) {

        DoacoesDTO doacaoCriada =
                doacoesService.criarDoacao(idAgendamento, doacoesDTO);

        if (doacaoCriada != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Doação cadastrada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível cadastrar a doação. Verifique se o agendamento existe, está disponível e ainda não possui uma doação.");
        }
    }

    // Buscar todas as doações
    @GetMapping("/buscar")
    public ResponseEntity<List<DoacoesDTO>> buscarDoacoes() {
        List<DoacoesDTO> doacoes = doacoesService.buscarDoacoes();

        return ResponseEntity.ok(doacoes);
    }

    // Buscar doação por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDoacaoPorId(@PathVariable Long id) {
        DoacoesDTO doacao = doacoesService.buscarDoacaoPorId(id);

        if (doacao != null) {
            return ResponseEntity.ok(doacao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doação não encontrada.");
        }
    }

    // Excluir doação por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarDoacao(@PathVariable Long id) {

        boolean doacaoDeletada = doacoesService.deletarDoacao(id);

        if (doacaoDeletada) {
            return ResponseEntity.ok(
                    "Doação com ID " + id + " foi deletada com sucesso!"
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doação com ID " + id + " não foi encontrada.");
        }
    }
}
