package com.fiap.techchallenge.parquimetro.dominio.pagamento.repository;

import com.fiap.techchallenge.parquimetro.dominio.pagamento.entitie.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {

    Pagamento findByIdAndAndIdusuario(Long id, Long idUsuario);
}
