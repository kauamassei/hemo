package dev.kauamassei.hemo.controller;

import dev.kauamassei.hemo.dto.DoadorDTO;
import dev.kauamassei.hemo.service.DoadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doador")
public class DoadorController {

    private DoadorService doadorService;

    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarDoador(@RequestBody DoadorDTO doadorDTO) {
        DoadorDTO doadorCriado = doadorService.criarDoador(doadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Doador criado com sucesso!");
    }

}
