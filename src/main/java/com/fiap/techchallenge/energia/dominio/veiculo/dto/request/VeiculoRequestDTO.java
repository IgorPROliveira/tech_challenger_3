package com.fiap.techchallenge.energia.dominio.veiculo.dto.request;

import com.fiap.techchallenge.energia.dominio.veiculo.entitie.Veiculo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Representa um objeto de  Veiculo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoRequestDTO {
    @ApiModelProperty(value = "Informacao da placa do veiculo", example = "BRA2E19", position = 1)
    @NotBlank(message = "Placa deve ser preenchido")
    private String placa;
    @ApiModelProperty(value = "Informacao da marca do veiculo", example = "renault", position = 1)
    @NotBlank(message = "Marca deve ser preenchido")
    private String marca;
    @ApiModelProperty(value = "Informacao do modelo do veiculo", example = "SUV", position = 1)
    @NotBlank(message = "Telefone deve ser preenchido")
    private String modelo;
    @ApiModelProperty(value = "Código  com o ID do usuario", example = "1", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idusuario;

    public Veiculo toEntity(){
        Veiculo veiculo = new Veiculo();

        veiculo.setPlaca(this.placa);
        veiculo.setMarca(this.marca);
        veiculo.setModelo(this.modelo);
        veiculo.setIdusuario(this.idusuario);

        return veiculo;
    }

    public void ToMapperEntity(Veiculo entity) {

        entity.setPlaca(this.placa);
        entity.setMarca(this.marca);
        entity.setModelo(this.modelo);
        entity.setIdusuario(this.idusuario);
    }
}