package com.fiap.techchallenge.energia.dominio.veiculo.entitie;

import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import com.fiap.techchallenge.energia.dominio.veiculo.dto.response.VeiculoDTO;
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
@Table(name="veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Long idusuario;

    @ManyToOne
    @JoinColumn(name = "idusuario" ,insertable=false, updatable=false)
    private Usuario usuario;

    public VeiculoDTO ToVeiculoDTO(){

        VeiculoDTO veiculoDTO = new VeiculoDTO();

        veiculoDTO.setId(this.id);
        veiculoDTO.setPlaca(this.placa);
        veiculoDTO.setMarca(this.marca);
        veiculoDTO.setModelo(this.modelo);
        veiculoDTO.setIdusuario(this.idusuario);
        return veiculoDTO;
    }
}
