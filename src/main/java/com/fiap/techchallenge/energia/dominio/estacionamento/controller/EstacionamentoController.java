package com.fiap.techchallenge.energia.dominio.estacionamento.controller;

import com.fiap.techchallenge.energia.dominio.estacionamento.Modalidade;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.request.EstacionamentoRequestDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    @Autowired
    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @PostMapping("/{modalidade}")
    public ResponseEntity save(@PathVariable("modalidade") Modalidade modalidade, @Valid @RequestBody EstacionamentoRequestDTO dto) {
        try {
            var estacionamento = estacionamentoService.save(dto, modalidade);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((estacionamento.getId())).toUri();
            return ResponseEntity.created(uri).body(estacionamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity encerrarEstacionamento( @PathVariable Long id) {
        var endereco = estacionamentoService.update(id);
        return ResponseEntity.ok(endereco);
    }

//    @GetMapping
//    public ResponseEntity<Page<EnderecoEletrodomesticoDTO>> findAll(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
//    {
//        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
//        var enderecoDTO = enderecoService.findAll(pageRequest);
//        return ResponseEntity.ok().body(enderecoDTO);
//    }
//

//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        enderecoService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
////    @GetMapping("/{id}")
////    public ResponseEntity<EnderecoEletrodomesticoDTO> findById(@PathVariable Long id) {
////        var endereco = enderecoService.findById(id);
////        return ResponseEntity.ok(endereco);
////    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<EnderecoEletrodomesticoDTO> findById(@PathVariable Long id) {
//        return enderecoService.findById(id);
//    }
//
////    @GetMapping("/Pesquisar")
////    public ResponseEntity<List<EnderecoEletrodomesticoDTO>> findByParam(
////            @RequestParam String rua,
////            @RequestParam String bairro,
////            @RequestParam String municipio
////    ) {
////        var endereco = enderecoService.findByParam(rua, bairro, municipio);
////        return ResponseEntity.ok(endereco);
////    }
//
//    @GetMapping("/Pesquisar")
//    public ResponseEntity<List<EnderecoEletrodomesticoDTO>> findByParam(
//            @RequestParam(required = false) String rua,
//            @RequestParam(required = false) String bairro,
//            @RequestParam(required = false) String municipio
//    ) {
//        return enderecoService.findByParam(rua, bairro, municipio);
//    }
}
