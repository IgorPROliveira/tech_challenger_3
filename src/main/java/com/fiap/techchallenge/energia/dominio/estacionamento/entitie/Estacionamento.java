package com.fiap.techchallenge.energia.dominio.estacionamento.entitie;

import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoEncerradoDTO;
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
    private Long tempo;
    private String modalidade;
    private LocalDateTime datainicio;
    private LocalDateTime datafim;
    private Double valor;
    private Boolean pago;
    private Long idusuario;
    private Long idveiculo;

    @ManyToOne
    @JoinColumn(name = "idusuario" ,insertable=false, updatable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idveiculo" ,insertable=false, updatable=false)
    private Veiculo veiculo;

    public EstacionamentoDTO ToEstacionamentoDTO() {
        EstacionamentoDTO estacionamentoDTO = new EstacionamentoDTO();

        estacionamentoDTO.setId(this.id);
        estacionamentoDTO.setLatitude(this.latitude);
        estacionamentoDTO.setLongitude(this.longitude);
        estacionamentoDTO.setTempo(this.tempo);
        estacionamentoDTO.setModalidade(this.modalidade);
        estacionamentoDTO.setDatainicio(this.datainicio);
        estacionamentoDTO.setDatafim(this.datafim);
        estacionamentoDTO.setValor(this.valor);
        estacionamentoDTO.setPago(this.pago);
        estacionamentoDTO.setIdusuario(this.idusuario);
        estacionamentoDTO.setIdveiculo(this.idveiculo);

        return estacionamentoDTO;
    }

    public EstacionamentoEncerradoDTO ToEstacionamentoEncerradoDTO(String aviso) {
        EstacionamentoEncerradoDTO estacionamentoEncerradoDTO = new EstacionamentoEncerradoDTO();

        estacionamentoEncerradoDTO.setId(this.id);
        estacionamentoEncerradoDTO.setLatitude(this.latitude);
        estacionamentoEncerradoDTO.setLongitude(this.longitude);
        estacionamentoEncerradoDTO.setTempo(this.tempo);
        estacionamentoEncerradoDTO.setModalidade(this.modalidade);
        estacionamentoEncerradoDTO.setDatainicio(this.datainicio);
        estacionamentoEncerradoDTO.setDatafim(this.datafim);
        estacionamentoEncerradoDTO.setValorTotal(this.valor);
        estacionamentoEncerradoDTO.setStatus("Aguardando pagamento");
        estacionamentoEncerradoDTO.setAviso(aviso);

        return estacionamentoEncerradoDTO;
    }
}
