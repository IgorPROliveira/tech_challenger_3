package com.fiap.techchallenge.energia.dominio.pagamento.dto.request;

import com.fiap.techchallenge.energia.dominio.pagamento.TipoPagamento;
import com.fiap.techchallenge.energia.dominio.pagamento.entitie.Pagamento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Representa um objeto de Pagamento")
public class PagamentoRequestDTO {
    @ApiModelProperty(value = "Tipo de pagamento (CARTAO_CREDITO, CARTAO_DEBITO, PIX)", example = "CARTAO_CREDITO", position = 1)
    @NotBlank(message = "Tipo de pagamento deve ser preenchido")
    private TipoPagamento tipoPagamento; // Pode ser uma string representando o tipo de pagamento
    @ApiModelProperty(value = "Valor do pagamento", example = "100.00", position = 1)
    @NotNull(message = "Valor do pagamento deve ser preenchido")
    private Double valor;
    @ApiModelProperty(value = "Número para pagamento (se aplicável)", example = "1234-5678-9012-3456", position = 1)
    private String numeroParaPagamento;
    @ApiModelProperty(value = "Data do pagamento (opcional)", example = "2023-10-25", position = 1)
    private LocalDate dataPagamento;
    public Pagamento toEntity() {
        Pagamento pagamento = new Pagamento();

        pagamento.setTipoPagamento(this.tipoPagamento);
        pagamento.setValor(this.valor);
        pagamento.setNumeroParaPagamento(this.numeroParaPagamento);
        pagamento.setDataPagamento(this.dataPagamento);


        return pagamento;
    }

    public void ToMapperEntity(Pagamento entity) {
        entity.setTipoPagamento(this.tipoPagamento);
        entity.setValor(this.valor);
        entity.setNumeroParaPagamento(this.numeroParaPagamento);
        entity.setDataPagamento(this.dataPagamento);        // Mapeie outros campos da entidade de pagamento aqui, se necessário.
    }
}
