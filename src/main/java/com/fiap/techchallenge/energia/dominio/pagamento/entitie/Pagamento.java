package com.fiap.techchallenge.energia.dominio.pagamento.entitie;

import com.fiap.techchallenge.energia.dominio.pagamento.Tipo;

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
@Table(name="tb_forma_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Defina o tipo de enumeração (ou outro tipo apropriado)
    private Tipo tipoPagamento;

    public PagamentoDTO ToPagamentoDTO(){
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        pagamentoDTO.setId(this.id);
        pagamentoDTO.setTipoPagamento(String.valueOf(this.tipoPagamento));

        return pagamentoDTO;
    }
}
