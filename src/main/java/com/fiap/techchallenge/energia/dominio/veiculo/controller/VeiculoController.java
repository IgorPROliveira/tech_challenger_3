package com.fiap.techchallenge.energia.dominio.veiculo.controller;


import com.fiap.techchallenge.energia.dominio.veiculo.dto.request.VeiculoRequestDTO;
import com.fiap.techchallenge.energia.dominio.veiculo.dto.response.VeiculoDTO;
import com.fiap.techchallenge.energia.dominio.veiculo.dto.response.VeiculoEstacionamentoDTO;
import com.fiap.techchallenge.energia.dominio.veiculo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/veiculo")

public class VeiculoController {

    private final VeiculoService veiculoService;


    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;

    }

    @GetMapping
    public ResponseEntity<Page<VeiculoEstacionamentoDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var veiculosDTO = veiculoService.findAll(pageRequest);
        return ResponseEntity.ok().body(veiculosDTO);

    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> save(@Valid @RequestBody VeiculoRequestDTO dto) {
        var veiculo = veiculoService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((veiculo.getId())).toUri();
        return ResponseEntity.created(uri).body(veiculo);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
//        var pessoa = pessoaService.findById(id);
//        return ResponseEntity.ok(pessoa);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id) {
        return veiculoService.findById(id);
    }

//    @GetMapping("/Pesquisar")
//    public ResponseEntity<List<PessoaDTO>> findByParam(
//            @RequestParam (required = false)String nome,
//            @RequestParam (required = false)String parentesco,
//            @RequestParam (required = false)String sexo
//    ) {
//        var pessoa = pessoaService.findByParam(nome, parentesco, sexo);
//        return ResponseEntity.ok(pessoa);
//    }

    @GetMapping("/Pesquisar")
    public ResponseEntity<VeiculoDTO> findByPlaca(
            @RequestParam(required = false) String placa
           ) {
        return veiculoService.findByPlaca(placa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> update(
            @Valid @RequestBody VeiculoRequestDTO dto,
            @PathVariable Long id) {
        var veiculo = veiculoService.update(id, dto);
        return ResponseEntity.ok(veiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
