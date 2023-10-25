package com.fiap.techchallenge.energia.dominio.pagamento.service;

import com.fiap.techchallenge.energia.dominio.pagamento.dto.request.PagamentoRequestDTO;
import com.fiap.techchallenge.energia.dominio.pagamento.dto.response.PagamentoDTO;
import com.fiap.techchallenge.energia.dominio.pagamento.entitie.Pagamento;
import com.fiap.techchallenge.energia.dominio.pagamento.repository.IPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
    private final IPagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoService(IPagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }
    public Pagamento realizarPagamento(PagamentoRequestDTO pagamentoRequest) {
        // Implemente a lógica de processamento de pagamento aqui
        // Isso pode incluir validações, cálculos, interações com sistemas de pagamento, etc.

        // Crie uma instância de Pagamento a partir do PagamentoRequestDTO
        Pagamento pagamento = pagamentoRequest.toEntity();

        // Salve o pagamento no banco de dados
        return pagamentoRepository.save(pagamento);
    }
    public List<PagamentoDTO> listarPagamentos() {
        // Implemente a lógica para listar todos os pagamentos do banco de dados
        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        // Converta as entidades de pagamento em DTOs
        return pagamentos.stream()
                .map(PagamentoDTO::new)
                .collect(Collectors.toList());
    }
}
