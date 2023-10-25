package com.fiap.techchallenge.energia.dominio.veiculo.service;

import com.fiap.techchallenge.energia.dominio.pessoa.dto.response.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.veiculo.dto.request.VeiculoRequestDTO;
import com.fiap.techchallenge.energia.dominio.veiculo.dto.response.VeiculoDTO;
import com.fiap.techchallenge.energia.dominio.veiculo.entitie.Veiculo;
import com.fiap.techchallenge.energia.dominio.veiculo.repository.IVeiculoRepository;
import com.fiap.techchallenge.energia.exception.service.DatabaseException;
import com.fiap.techchallenge.energia.exception.service.ServiceNotFoundedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private static IVeiculoRepository veiculoRepository;

    @Transactional
    public VeiculoDTO save(VeiculoRequestDTO dto) {
        var entity = dto.toEntity();
        var veiculoSaved = veiculoRepository.save(entity);
        return veiculoSaved.ToVeiculoDTO();
    }

    @Transactional(readOnly = true)
    public Page<VeiculoDTO> findAll(PageRequest pageRequest) {
        var veiculos = veiculoRepository.findAll(pageRequest);
        return veiculos.map(Veiculo::ToVeiculoDTO);
    }

    @Transactional
    public VeiculoDTO update(Long id, VeiculoRequestDTO dto) {
        try {
            Veiculo entity = veiculoRepository.getReferenceById(id);
            dto.ToMapperEntity(entity);

            var veiculoSaved = veiculoRepository.save(entity);
            return veiculoSaved.ToVeiculoDTO();

        } catch (EntityNotFoundException e) {
            throw new ServiceNotFoundedException("Veiculo não encontrado, Placa: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            veiculoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<VeiculoDTO> findById(Long id) {
        var veiculo = veiculoRepository.findById(id);
        if (veiculo.isPresent()) {
            return ResponseEntity.ok(veiculo.get().ToVeiculoDTO());
        }
        return ResponseEntity.notFound().build();
    }

    //    @Transactional(readOnly = true)
//    public ResponseEntity<VeiculoDTO> findByPlaca(String placa) {
//        var veiculo = veiculoRepository.findByPlaca(placa);
//        if(veiculo.isPresent()) {
//            return ResponseEntity.ok(veiculo.get().ToVeiculoDTO());
//        }
//        return ResponseEntity.notFound().build();
//    }
    @Transactional(readOnly = true)
    public ResponseEntity<VeiculoDTO> findByPlaca(String placa) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findByPlaca(placa);
        if (veiculoOptional.isPresent()) {
            VeiculoDTO veiculoDTO = veiculoOptional.get().ToVeiculoDTO();
            return ResponseEntity.ok(veiculoDTO);
        }
        return ResponseEntity.notFound().build();

    }

}