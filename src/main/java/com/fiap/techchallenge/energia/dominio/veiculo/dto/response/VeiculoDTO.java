package com.fiap.techchallenge.energia.dominio.veiculo.dto.response;

import com.fiap.techchallenge.energia.dominio.veiculo.entitie.Veiculo;
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
public class VeiculoDTO {

    private Long id;
    @ApiModelProperty(value = "Informacao da placa do veiculo", example = "BRA2E19", position = 1)
    @NotBlank(message = "Placa deve ser preenchido")
    private String placa;
    @ApiModelProperty(value = "Informacao da marca do veiculo", example = "renault", position = 1)
    @NotBlank(message = "Marca deve ser preenchido")
    private String marca;
    @ApiModelProperty(value = "Informacao do modelo do veiculo", example = "SUV", position = 1)
    @NotBlank(message = "Telefone deve ser preenchido")
    private String modelo;

    public VeiculoDTO(Veiculo veiculo){
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
    }
}
