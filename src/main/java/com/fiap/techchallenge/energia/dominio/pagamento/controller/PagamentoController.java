package com.fiap.techchallenge.energia.dominio.pagamento.controller;

import com.fiap.techchallenge.energia.dominio.pagamento.dto.request.PagamentoRequestDTO;
import com.fiap.techchallenge.energia.dominio.pagamento.dto.response.PagamentoDTO;
import com.fiap.techchallenge.energia.dominio.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;

    }

    @GetMapping
    public ResponseEntity<Page<PagamentoDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var pagamentosDTO = pagamentoService.findAll(pageRequest);
        return ResponseEntity.ok().body(pagamentosDTO);

    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> save(@Valid @RequestBody PagamentoRequestDTO dto) {
        var pagamento = pagamentoService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((pagamento.getId())).toUri();
        return ResponseEntity.created(uri).body(pagamento);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        return pagamentoService.findById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

