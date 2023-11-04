package com.fiap.techchallenge.parquimetro.dominio.estacionamento.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.entitie.Estacionamento;
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

    @ApiModelProperty(value = "Tempo em horas inteiras", example = "1", position = 1)
    private Long tempo;

    @ApiModelProperty(value = "Código  com o ID do usuario", example = "1", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idusuario;

    @ApiModelProperty(value = "Código  com o ID do veiculo", example = "1", position = 1)
    @NotNull(message = "id do veiculo deve ser preenchido")
    private Long idveiculo;

    @JsonIgnore
    private String modalidade;

    @JsonIgnore
    private LocalDateTime datainico;

    @JsonIgnore
    private LocalDateTime datafim;

    @JsonIgnore
    private Double valor;

    @JsonIgnore
    private Boolean pago;


    public Estacionamento toEntity() {
        Estacionamento estacionamento = new Estacionamento();

        estacionamento.setLatitude(this.latitude);
        estacionamento.setLongitude(this.longitude);
        estacionamento.setTempo(this.tempo);
        estacionamento.setModalidade(this.modalidade);
        estacionamento.setDatainicio(this.datainico);
        estacionamento.setDatafim(this.datafim);
        estacionamento.setTempo(this.tempo);
        estacionamento.setValor(this.valor);
        estacionamento.setPago(this.pago);
        estacionamento.setIdusuario(this.idusuario);
        estacionamento.setIdveiculo(this.idveiculo);

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
//        endereco.setidusuario(this.idusuario);
//    }
}
