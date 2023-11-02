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
import javax.validation.constraints.NotNull;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Representa um objeto de Pagamento")
public class PagamentoRequestDTO {
    @ApiModelProperty(value = "Tipo de pagamento (CARTAO_CREDITO, CARTAO_DEBITO, PIX)", example = "CARTAO_CREDITO", position = 1)
    @NotNull(message = "Tipo de pagamento deve ser preenchido")
    private Tipo tipo; // Pode ser uma string representando o tipo de pagamento
    @ApiModelProperty(value = "Código  com o ID do usuario", example = "1", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idusuario;

    public Pagamento toEntity() {
        Pagamento pagamento = new Pagamento();

        pagamento.setTipo(this.tipo);
        pagamento.setIdusuario(this.idusuario);
        return pagamento;
    }

    public void ToMapperEntity(Pagamento entity) {
        entity.setTipo(this.tipo);
        entity.setIdusuario(this.idusuario);
    }
}
