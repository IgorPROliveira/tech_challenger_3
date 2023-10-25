package com.fiap.techchallenge.energia.dominio.pagamento.entitie;

import com.fiap.techchallenge.energia.dominio.pagamento.TipoPagamento;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoPagamento tipoPagamento;

//    public Pagamento ToPagamentoDTO(){
//        PagamentoDTO pagamentoDTO = new PagamentoDTO();
//
//        pagamentoDTO.setId(this.id);
//        pagamentoDTO.setId(this.tipoPagamento);
//
//        return pagamentoDTO;
//    }
}
