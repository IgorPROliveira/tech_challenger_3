package com.fiap.techchallenge.energia.dominio.estacionamento.service;

import com.fiap.techchallenge.energia.dominio.estacionamento.Modalidade;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.request.EstacionamentoRequestDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoAlertaDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.dto.response.EstacionamentoEncerradoDTO;
import com.fiap.techchallenge.energia.dominio.estacionamento.repository.IEstacionamentoRepository;
import com.fiap.techchallenge.energia.dominio.pagamento.Tipo;
import com.fiap.techchallenge.energia.dominio.pagamento.entitie.Pagamento;
import com.fiap.techchallenge.energia.dominio.pagamento.repository.IPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class EstacionamentoService {

    private final IEstacionamentoRepository estacionamentoRepository;
    private final IPagamentoRepository pagamentoRepository;

    @Autowired
    public EstacionamentoService(IEstacionamentoRepository estacionamentoRepository, IPagamentoRepository pagamentoRepository) {
        this.estacionamentoRepository = estacionamentoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }


    @Transactional
    public EstacionamentoDTO save(EstacionamentoRequestDTO estacionamentoDTO, Modalidade modalidade) throws Exception {
        estacionamentoDTO.setDatainico(LocalDateTime.now());
        estacionamentoDTO.setPago(false);
        estacionamentoDTO.setModalidade(modalidade.toString());
        if (modalidade.equals(Modalidade.FIXO)) {
            if (estacionamentoDTO.getTempo() == null || estacionamentoDTO.getTempo().equals(0L)) {
                throw new Exception("O campo tempo deve ser preenchido na modalidade por tempo FIXO");
            } else {
                estacionamentoDTO.setDatafim(estacionamentoDTO.getDatainico().plusMinutes(estacionamentoDTO.getTempo() * 60));
                estacionamentoDTO.setValor(7.5 * estacionamentoDTO.getTempo());
            }
        } else {
            estacionamentoDTO.setTempo(null);
        }

        var estacionamentoEntity = estacionamentoDTO.toEntity();
        var estacionamentoSaved = estacionamentoRepository.save(estacionamentoEntity);
        return estacionamentoSaved.ToEstacionamentoDTO("Estacionamento iniciado com sucesso!");
    }

    @Transactional
    public EstacionamentoEncerradoDTO encerrarEstacionamento(Long id) throws Exception {
        var estacionamentoEntity = estacionamentoRepository.findById(id);
        if (estacionamentoEntity.isPresent() && !estacionamentoEntity.get().getPago()) {
            LocalDateTime dataEncerramento = LocalDateTime.now();
            if (estacionamentoEntity.get().getModalidade().equals("FIXO")) {
                if (estacionamentoEntity.get().getDatafim().isAfter(dataEncerramento)) {
                    return estacionamentoEntity.get().ToEstacionamentoEncerradoDTO("Aguardando pagamento", "Encerrado dentro do tempo");
                } else {
                    Long diferenca = ChronoUnit.MINUTES.between(estacionamentoEntity.get().getDatafim(), dataEncerramento);
                    if (diferenca > 60) {
                        Double horas = diferenca / 60.0d;
                        int valorArredondado = (int) Math.ceil(horas);
                        estacionamentoEntity.get().setTempo(estacionamentoEntity.get().getTempo() + valorArredondado);
                        estacionamentoEntity.get().setDatafim(dataEncerramento);
                        estacionamentoEntity.get().setValor(estacionamentoEntity.get().getTempo() * 7.5);
                        estacionamentoRepository.save(estacionamentoEntity.get());
                        return estacionamentoEntity.get().ToEstacionamentoEncerradoDTO("Aguardando pagamento", "Estacionamento encerrado após o período: Será cobrado " + valorArredondado + " hora a mais!");
                    } else {
                        estacionamentoEntity.get().setTempo(estacionamentoEntity.get().getTempo() + 1);
                        estacionamentoEntity.get().setDatafim(dataEncerramento);
                        estacionamentoEntity.get().setValor(estacionamentoEntity.get().getTempo() * 7.5);
                        estacionamentoRepository.save(estacionamentoEntity.get());
                        return estacionamentoEntity.get().ToEstacionamentoEncerradoDTO("Aguardando pagamento", "Estacionamento encerrado após o período: Será cobrado 1 hora a mais!");
                    }
                }
            } else {
                Long diferenca = ChronoUnit.MINUTES.between(estacionamentoEntity.get().getDatainicio(), dataEncerramento);
                Double horas = diferenca / 60.0d;
                if (horas > 15.0d) {
                    int valorArredondado = (int) Math.ceil(horas);
                    estacionamentoEntity.get().setTempo(Long.valueOf(valorArredondado));
                    estacionamentoEntity.get().setDatafim(dataEncerramento);
                    estacionamentoEntity.get().setValor(estacionamentoEntity.get().getTempo() * 7.5);
                    estacionamentoRepository.save(estacionamentoEntity.get());
                    return estacionamentoEntity.get().ToEstacionamentoEncerradoDTO("Aguardando pagamento", "Estacionamento encerrado após " + valorArredondado + " horas");
                } else {
                    estacionamentoEntity.get().setTempo(0L);
                    estacionamentoEntity.get().setDatafim(dataEncerramento);
                    estacionamentoEntity.get().setValor(0d);
                    estacionamentoEntity.get().setPago(true);
                    estacionamentoRepository.save(estacionamentoEntity.get());
                    return estacionamentoEntity.get().ToEstacionamentoEncerradoDTO("PAGO", "Não foi atingido o tempo para cobrança");
                }

            }
        } else {
            throw new Exception("Não foi encontrado um estacionamento com o ID informado ou o Mesmo já foi encerrado!");
        }
    }

    public EstacionamentoAlertaDTO notificar(Long id) throws Exception {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        var estacionamentoEntity = estacionamentoRepository.findById(id);
        if (estacionamentoEntity.isPresent()) {
            if (estacionamentoEntity.get().getPago()) {
                return estacionamentoEntity.get().ToEstacionamentoAlertaDTO("Este estacionamento já foi encerrado!");
            } else {
                if (estacionamentoEntity.get().getModalidade().equals("FIXO")) {
                    if (dataHoraAtual.isAfter(estacionamentoEntity.get().getDatafim())) {
                        return estacionamentoEntity.get().ToEstacionamentoAlertaDTO("O tempo de estacionamento contratado já esgotou! Será adicionado automaticamente mais 1 hora!");
                    }
                    Long diferenca = ChronoUnit.MINUTES.between(dataHoraAtual, estacionamentoEntity.get().getDatafim());
                    return estacionamentoEntity.get().ToEstacionamentoAlertaDTO("Falta " + diferenca + " minutos para acabar seu tempo de estacionamento");
                } else {

                    Long diferenca = ChronoUnit.HOURS.between(estacionamentoEntity.get().getDatainicio(), dataHoraAtual);
                    if (diferenca.equals(0)) {
                        diferenca = ChronoUnit.MINUTES.between(estacionamentoEntity.get().getDatainicio(), dataHoraAtual);
                        return estacionamentoEntity.get().ToEstacionamentoAlertaDTO("Você já está estacionado a " + diferenca + " minutos. Caso não encerre será cobrado automaticamente mais 1 hora!");
                    }
                    return estacionamentoEntity.get().ToEstacionamentoAlertaDTO("Você já está estacionado a " + diferenca + " horas. Caso não encerre será cobrado automaticamente mais 1 hora!");
                }
            }

        } else {
            throw new Exception("Não foi encontrado um estacionamento com o ID informado!");
        }
    }

    public EstacionamentoDTO pagarFinalizarEstacionamento(Long id, Long idPagamento) throws Exception {
        var estacionamentoEntity = estacionamentoRepository.findById(id);
        if (estacionamentoEntity.isPresent() && !estacionamentoEntity.get().getPago()) {
            Pagamento pagamento = pagamentoRepository.findByIdAndAndIdusuario(idPagamento, estacionamentoEntity.get().getIdusuario());
            if (pagamento != null) {
                if (estacionamentoEntity.get().getModalidade().equals("POR_HORA")) {
                    if (pagamento.getTipo().equals(Tipo.PIX)) {
                        throw new Exception("Não é possível pagar com PIX o estacionamento por tempo FIXO");
                    }
                }
                estacionamentoEntity.get().setIdpagamento(idPagamento);
                estacionamentoEntity.get().setPago(true);
                estacionamentoRepository.save(estacionamentoEntity.get());
                return estacionamentoEntity.get().ToEstacionamentoDTO("Estacionamento PAGO e encerrado com sucesso!");
            } else {
                throw new Exception("Não foi encontrado a forma de pagamento informada para o Usuario");
            }
        } else {
            throw new Exception("Não foi encontrado um estacionamento com o ID informado ou o Mesmo já foi encerrado!");
        }
    }
}

