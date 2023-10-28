package com.fiap.techchallenge.energia.dominio.pagamento.dto.request;

import com.fiap.techchallenge.energia.dominio.pagamento.Tipo;
import com.fiap.techchallenge.energia.dominio.pagamento.entitie.Pagamento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Representa um objeto de Pagamento")
public class PagamentoRequestDTO {
    @ApiModelProperty(value = "Tipo de pagamento (CARTAO_CREDITO, CARTAO_DEBITO, PIX)", example = "CARTAO_CREDITO", position = 1)
    @NotBlank(message = "Tipo de pagamento deve ser preenchido")
    private Tipo tipoPagamento; // Pode ser uma string representando o tipo de pagamento

    public Pagamento toEntity() {
        Pagamento pagamento = new Pagamento();

        pagamento.setTipoPagamento(this.tipoPagamento);

        return pagamento;
    }

    public void ToMapperEntity(Pagamento entity) {
        entity.setTipoPagamento(this.tipoPagamento);

    }
}
