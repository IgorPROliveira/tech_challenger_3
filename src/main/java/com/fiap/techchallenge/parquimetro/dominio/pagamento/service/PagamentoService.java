package com.fiap.techchallenge.parquimetro.dominio.pagamento.service;

import com.fiap.techchallenge.parquimetro.dominio.pagamento.dto.request.PagamentoRequestDTO;
import com.fiap.techchallenge.parquimetro.dominio.pagamento.dto.response.PagamentoDTO;
import com.fiap.techchallenge.parquimetro.dominio.pagamento.entitie.Pagamento;
import com.fiap.techchallenge.parquimetro.dominio.pagamento.repository.IPagamentoRepository;
import com.fiap.techchallenge.parquimetro.exception.service.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {
    private final IPagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoService(IPagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }
    @Transactional
    public PagamentoDTO save(PagamentoRequestDTO dto) {
        var entity = dto.toEntity();
        var pagamentoSaved = pagamentoRepository.save(entity);
        return pagamentoSaved.ToPagamentoDTO();
    }
    @Transactional(readOnly = true)
    public Page<PagamentoDTO> findAll(PageRequest pageRequest) {
        var pagamentos = pagamentoRepository.findAll(pageRequest);
        return  pagamentos.map(Pagamento::ToPagamentoDTO);
    }

    @Transactional
    public void delete(Long id)  {
        try {
            pagamentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PagamentoDTO> findById(Long id) {
        var pagamento = pagamentoRepository.findById(id);
        if(pagamento.isPresent()) {
            return ResponseEntity.ok(pagamento.get().ToPagamentoDTO());
        }
        return ResponseEntity.notFound().build();
    }
}
