package com.fiap.techchallenge.energia.dominio.veiculo.repository;

import com.fiap.techchallenge.energia.dominio.veiculo.entitie.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);
}
