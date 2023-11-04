package com.fiap.techchallenge.parquimetro.dominio.pagamento.entitie;

import com.fiap.techchallenge.parquimetro.dominio.pagamento.Tipo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fiap.techchallenge.parquimetro.dominio.pagamento.dto.response.PagamentoDTO;
import com.fiap.techchallenge.parquimetro.dominio.usuario.entitie.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING) // Defina o tipo de enumeração (ou outro tipo apropriado)
    private Tipo tipo;
    private Long idusuario;

    @ManyToOne
    @JoinColumn(name = "idusuario" ,insertable=false, updatable=false)
    private Usuario usuario;

    public PagamentoDTO ToPagamentoDTO(){
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        pagamentoDTO.setId(this.id);
        pagamentoDTO.setTipo(String.valueOf(this.tipo));
        pagamentoDTO.setIdusuario(this.idusuario);

        return pagamentoDTO;
    }
}
