package com.fiap.techchallenge.energia.dominio.estacionamento.repository;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.energia.dominio.estacionamento.entitie.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEstacionamentoRepository extends JpaRepository<Estacionamento,Long> {

}
