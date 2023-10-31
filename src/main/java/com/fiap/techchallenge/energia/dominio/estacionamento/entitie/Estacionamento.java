package com.fiap.techchallenge.energia.dominio.estacionamento.entitie;

import com.fiap.techchallenge.energia.dominio.endereco.dto.response.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoDTO;
import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import com.fiap.techchallenge.energia.dominio.veiculo.entitie.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="estacionamento")
public class Estacionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String latitude;
    private String longitude;
    private String modalidade;
    private Long tempo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Double valor;
    private Boolean pago;
    private Long idUsuario;
    private Long idVeiculo;

    @ManyToOne
    @JoinColumn(name = "idUsuario" ,insertable=false, updatable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idVeiculo" ,insertable=false, updatable=false)
    private Veiculo veiculo;

    public EstacionamentoDTO ToEstacionamentoDTO() {
        EstacionamentoDTO estacionamentoDTO = new EstacionamentoDTO();

        estacionamentoDTO.setId(this.id);
        estacionamentoDTO.setLatitude(this.latitude);
        estacionamentoDTO.setLongitude(this.longitude);
        estacionamentoDTO.setModalidade(this.modalidade);
        estacionamentoDTO.setTempo(this.tempo);
        estacionamentoDTO.setDataInicio(this.dataInicio);
        estacionamentoDTO.setDataFim(this.dataFim);
        estacionamentoDTO.setValor(this.valor);
        estacionamentoDTO.setPago(this.pago);
        estacionamentoDTO.setIdUsuario(this.idUsuario);
        estacionamentoDTO.setIdVeiculo(this.idVeiculo);

        return estacionamentoDTO;
    }
}
