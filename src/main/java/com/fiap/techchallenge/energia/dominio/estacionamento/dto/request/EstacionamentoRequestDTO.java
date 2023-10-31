package com.fiap.techchallenge.energia.dominio.estacionamento.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.techchallenge.energia.dominio.estacionamento.entitie.Estacionamento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(description = "Representa um objeto de  Estacionamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstacionamentoRequestDTO {
    @ApiModelProperty(value = "Latitude", example = "-23º 33' 23.99 S", position = 1)
    @NotBlank(message = "A latitude deve ser preenchida")
    private String latitude;

    @ApiModelProperty(value = "Longitude", example = "-43º 39' 12.59 W", position = 1)
    @NotBlank(message = "A Longitude deve ser preenchida")
    private String longitude;

    @ApiModelProperty(value = "Modalidade", example = "POR_HORA", position = 1)
    @NotBlank(message = "A modalidade deve ser preenchida")
    private String modalidade;

    @ApiModelProperty(value = "Tempo em horas inteiras", example = "60", position = 1)
    private Long tempo;

    @ApiModelProperty(value = "Código  com o ID do usuario", example = "1", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idUsuario;

    @ApiModelProperty(value = "Código  com o ID do veiculo", example = "1", position = 1)
    @NotNull(message = "id do veiculo deve ser preenchido")
    private Long idVeiculo;

    @JsonIgnore
    private LocalDateTime dataInico;

    @JsonIgnore
    private LocalDateTime dataFim;

    @JsonIgnore
    private Double valor;

    @JsonIgnore
    private Boolean pago;


    public Estacionamento toEntity() {
        Estacionamento estacionamento = new Estacionamento();

        estacionamento.setLatitude(this.latitude);
        estacionamento.setLongitude(this.longitude);
        estacionamento.setModalidade(this.modalidade);
        estacionamento.setTempo(this.tempo);
        estacionamento.setIdUsuario(this.idUsuario);
        estacionamento.setIdVeiculo(this.idVeiculo);
        estacionamento.setDataInicio(this.dataInico);
        estacionamento.setDataFim(this.dataFim);
        estacionamento.setTempo(this.tempo);
        estacionamento.setValor(this.valor);
        estacionamento.setPago(this.pago);
        estacionamento.setIdUsuario(this.idUsuario);
        estacionamento.setIdVeiculo(this.idVeiculo);

        return estacionamento;
    }

//    public void ToMapperEntity(Endereco endereco) {
//        endereco.setPais(this.pais);
//        endereco.setEstado(this.estado);
//        endereco.setMunicipio(this.municipio);
//        endereco.setBairro(this.bairro);
//        endereco.setRua(this.rua);
//        endereco.setCep(this.cep);
//        endereco.setComplemento(this.complemento);
//        endereco.setIdusuario(this.idusuario);
//    }
}
