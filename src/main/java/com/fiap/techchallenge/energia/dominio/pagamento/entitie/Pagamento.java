package com.fiap.techchallenge.energia.dominio.pagamento.entitie;

import com.fiap.techchallenge.energia.dominio.pagamento.TipoPagamento;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fiap.techchallenge.energia.dominio.pagamento.dto.response.PagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Defina o tipo de enumeração (ou outro tipo apropriado)
    private TipoPagamento tipoPagamento;
    private Double valor;
    private String numeroParaPagamento;
    private LocalDate dataPagamento;
    public PagamentoDTO ToPagamentoDTO(){
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        pagamentoDTO.setId(this.id);
        pagamentoDTO.setTipoPagamento(String.valueOf(this.tipoPagamento));
        pagamentoDTO.setValor(this.valor);
        pagamentoDTO.setNumeroParaPagamento(this.numeroParaPagamento);
        pagamentoDTO.setDataPagamento(this.dataPagamento);

        return pagamentoDTO;
    }
}
