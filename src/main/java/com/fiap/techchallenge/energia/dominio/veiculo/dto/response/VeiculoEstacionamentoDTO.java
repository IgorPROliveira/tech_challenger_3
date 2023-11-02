package com.fiap.techchallenge.energia.dominio.veiculo.dto.response;

import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoDTO;
import com.fiap.techchallenge.energia.dominio.veiculo.entitie.Veiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoEstacionamentoDTO {
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Long idusuario;
    private List<EstacionamentoDTO> estacionamentos = new ArrayList<>();

    public VeiculoEstacionamentoDTO(Veiculo veiculo){
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.idusuario = veiculo.getIdusuario();
        veiculo.getEstacionamento().forEach(veicul -> this.estacionamentos.add(new EstacionamentoDTO(veicul)));
    }
}
