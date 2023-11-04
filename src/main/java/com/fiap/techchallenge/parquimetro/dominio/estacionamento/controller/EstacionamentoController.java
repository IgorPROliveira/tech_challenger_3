package com.fiap.techchallenge.parquimetro.dominio.estacionamento.controller;

import com.fiap.techchallenge.parquimetro.dominio.estacionamento.Modalidade;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.dto.request.EstacionamentoRequestDTO;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.dto.response.EstacionamentoAlertaDTO;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.dto.response.EstacionamentoDTO;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.dto.response.EstacionamentoEncerradoDTO;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    @Autowired
    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @PostMapping("/{modalidade}")
    public ResponseEntity<EstacionamentoDTO> inciar(@PathVariable("modalidade") Modalidade modalidade, @Valid @RequestBody EstacionamentoRequestDTO dto) {
        try {
            var estacionamento = estacionamentoService.save(dto, modalidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(estacionamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EstacionamentoDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<EstacionamentoAlertaDTO> alertaTempoEstacionamento(@RequestParam Long id){
        try {
            var estacionamento = estacionamentoService.notificar(id);
            return ResponseEntity.ok().body(estacionamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EstacionamentoAlertaDTO(e.getMessage()));
        }
    }

    @PutMapping("encerrar/{id}")
    public ResponseEntity<EstacionamentoEncerradoDTO> encerrar(@PathVariable Long id) {
        try {
            var estacionamento = estacionamentoService.encerrarEstacionamento(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(estacionamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EstacionamentoEncerradoDTO(e.getMessage()));
        }
    }

    @PutMapping("pagar/{id}")
    public ResponseEntity<EstacionamentoDTO> pagar(@PathVariable Long id, @RequestParam Long idPagamento) {
        try {
            var estacionamento = estacionamentoService.pagarFinalizarEstacionamento(id, idPagamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(estacionamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EstacionamentoDTO(e.getMessage()));
        }
    }
}
