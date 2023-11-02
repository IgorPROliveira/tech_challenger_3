package com.fiap.techchallenge.energia.dominio.estacionamento.dto.response;

import com.fiap.techchallenge.energia.dominio.estacionamento.entitie.Estacionamento;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstacionamentoDTO {

    @ApiModelProperty(value = "ID do endereco", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "Latitude", example = "-23º 33' 23.99 S", position = 1)
    @NotBlank(message = "A latitude deve ser preenchida")
    private String latitude;

    @ApiModelProperty(value = "Longitude", example = "-43º 39' 12.59 W", position = 1)
    @NotBlank(message = "A Longitude deve ser preenchida")
    private String longitude;

    @ApiModelProperty(value = "Modalidade", example = "POR_HORA", position = 1)
    @NotBlank(message = "A modalidade deve ser preenchida")
    private String modalidade;

    @ApiModelProperty(value = "Tempo em minutos", example = "60", position = 1)
    private Long tempo;

    @ApiModelProperty(value = "2023-10-30 10:00:00", example = "1", position = 1)
    @NotNull(message = "A data de inicio deve ser preenchida")
    private LocalDateTime datainicio;

    @ApiModelProperty(value = "2023-10-30 11:00:00", example = "1", position = 1)
    private LocalDateTime datafim;

    @ApiModelProperty(value = "Valor ", example = "7.00", position = 1)
    private Double valor;

    @ApiModelProperty(value = "S/N ", example = "N", position = 1)
    private Boolean pago;

    @ApiModelProperty(value = "Código  com o ID do usuario", example = "1", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idusuario;

    @ApiModelProperty(value = "Código  com o ID do veiculo", example = "1", position = 1)
    @NotNull(message = "id do veiculo deve ser preenchido")
    private Long idveiculo;

    public EstacionamentoDTO(Estacionamento estacionamento) {
        this.id = estacionamento.getId();
        this.latitude = estacionamento.getLatitude();
        this.longitude = estacionamento.getLongitude();
        this.modalidade = estacionamento.getModalidade();
        this.tempo = estacionamento.getTempo();
        this.datainicio = estacionamento.getDatainicio();
        this.datafim = estacionamento.getDatafim();
        this.valor = estacionamento.getValor();
        this.pago = estacionamento.getPago();
        this.idveiculo = estacionamento.getIdveiculo();

    }


    public EstacionamentoDTO(Object estacionamento) {
    }
}

