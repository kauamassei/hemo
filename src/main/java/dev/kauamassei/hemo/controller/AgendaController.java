package dev.kauamassei.hemo.controller;

import dev.kauamassei.hemo.dto.AgendaDTO;
import dev.kauamassei.hemo.service.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    // Buscar todos os agendamentos
    @GetMapping("/buscar")
    public ResponseEntity<List<AgendaDTO>> buscarAgendamentos() {
        List<AgendaDTO> agendas = agendaService.buscarAgendamentos();

        return ResponseEntity.ok(agendas);
    }

    // Buscar agendamento por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarAgendamentoPorId(@PathVariable Long id) {
        AgendaDTO agenda = agendaService.buscarAgendamentoPorId(id);

        if (agenda != null) {
            return ResponseEntity.ok(agenda);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Agendamento não encontrado.");
        }
    }

    // Alterar agendamento por ID
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarAgendamentoPorId(
            @PathVariable Long id,
            @RequestBody AgendaDTO agendaAtualizada) {

        AgendaDTO agenda = agendaService.alterarAgendamentoPorId(id, agendaAtualizada);

        if (agenda != null) {
            return ResponseEntity.ok(agenda);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Agendamento não encontrado.");
        }
    }

    // Cancelar agendamento
    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelarAgendamento(@PathVariable Long id) {

        boolean cancelado = agendaService.cancelarAgendamento(id);

        if (cancelado) {
            return ResponseEntity.ok(
                    "Agendamento com ID " + id + " cancelado com sucesso!"
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível cancelar o agendamento. Verifique se ele existe ou se já foi realizado/cancelado.");
        }
    }
}
