package dev.kauamassei.hemo.controller;

import dev.kauamassei.hemo.dto.AgendaDTO;
import dev.kauamassei.hemo.dto.DoacoesDTO;
import dev.kauamassei.hemo.dto.DoadorDTO;
import dev.kauamassei.hemo.service.DoadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doador")
public class DoadorController {

    private final DoadorService doadorService;

    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

    // Criar doador
    @PostMapping("/criar")
    public ResponseEntity<String> criarDoador(@RequestBody DoadorDTO doadorDTO) {
        DoadorDTO doadorCriado = doadorService.criarDoador(doadorDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Doador criado com sucesso!");
    }

    // Buscar todos os doadores
    @GetMapping("/buscar")
    public ResponseEntity<List<DoadorDTO>> buscarDoadores() {
        List<DoadorDTO> doadores = doadorService.buscarDoador();

        return ResponseEntity.ok(doadores);
    }

    // Buscar doador por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDoadorPorId(@PathVariable Long id) {
        DoadorDTO doador = doadorService.buscarDoadorPorId(id);

        if (doador != null) {
            return ResponseEntity.ok(doador);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doador não encontrado.");
        }
    }

    // Alterar doador por ID
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarDoadorPorId(
            @PathVariable Long id,
            @RequestBody DoadorDTO doadorAtualizado) {

        DoadorDTO doador =
                doadorService.alterarDoadorPorId(id, doadorAtualizado);

        if (doador != null) {
            return ResponseEntity.ok(doador);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doador não encontrado.");
        }
    }

    // Deletar doador por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarDoador(@PathVariable Long id) {

        DoadorDTO doador = doadorService.buscarDoadorPorId(id);

        if (doador != null) {
            doadorService.deletarDoador(id);

            return ResponseEntity.ok(
                    "Doador com ID " + id + " foi deletado com sucesso!"
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doador com ID " + id + " não foi encontrado.");
        }
    }

    // Consultar doações do doador
    @GetMapping("/{idDoador}/doacoes")
    public ResponseEntity<?> consultarDoacoes(@PathVariable Long idDoador) {

        List<DoacoesDTO> doacoes =
                doadorService.consultarDoacoes(idDoador);

        if (doacoes != null) {
            return ResponseEntity.ok(doacoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doador não encontrado.");
        }
    }

    // Agendar doação
    @PostMapping("/{idDoador}/agendar/{idUnidade}")
    public ResponseEntity<?> agendarDoacao(
            @PathVariable Long idDoador,
            @PathVariable Long idUnidade,
            @RequestBody AgendaDTO agendaDTO) {

        AgendaDTO agendaCriada =
                doadorService.agendarDoacao(idDoador, idUnidade, agendaDTO);

        if (agendaCriada != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(agendaCriada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível realizar o agendamento. Verifique se o doador e a unidade de coleta existem.");
        }
    }

    // Cancelar agendamento
    @DeleteMapping("/{idDoador}/cancelar/{idAgendamento}")
    public ResponseEntity<String> cancelarAgendamento(
            @PathVariable Long idDoador,
            @PathVariable Long idAgendamento) {

        boolean cancelado =
                doadorService.cancelarAgendamento(idDoador, idAgendamento);

        if (cancelado) {
            return ResponseEntity.ok(
                    "Agendamento com ID " + idAgendamento
                            + " cancelado com sucesso!"
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível cancelar o agendamento. Verifique se ele existe, pertence ao doador e ainda não foi cancelado.");
        }
    }
}
