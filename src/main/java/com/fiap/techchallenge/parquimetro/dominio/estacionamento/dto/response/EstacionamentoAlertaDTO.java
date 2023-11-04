package com.fiap.techchallenge.parquimetro.dominio.estacionamento.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstacionamentoAlertaDTO {

    public EstacionamentoAlertaDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    @ApiModelProperty(value = "Modalidade", example = "FIXO", position = 1)
    private String modalidade;

    @ApiModelProperty(value = "Mensagem", example = "Falta 5 minutos para acabar seu tempo de estacionamento", position = 1)
    private String mensagem;

}
