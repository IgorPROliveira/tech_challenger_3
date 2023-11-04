package com.fiap.techchallenge.parquimetro.dominio.estacionamento.repository;

import com.fiap.techchallenge.parquimetro.dominio.estacionamento.entitie.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstacionamentoRepository extends JpaRepository<Estacionamento,Long> {

}
