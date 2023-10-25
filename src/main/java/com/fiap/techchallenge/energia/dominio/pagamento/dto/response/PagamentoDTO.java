package com.fiap.techchallenge.energia.dominio.pagamento.dto.response;

import com.fiap.techchallenge.energia.dominio.pagamento.entitie.Pagamento;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    private Long id;
    @ApiModelProperty(value = "Informacao do tipo de pagamento", example = "pix", position = 1)
    @NotBlank(message = "tipo de pagamento deve ser preenchido")
    private String tipoPagamento;
    @ApiModelProperty(value = "Informacao do valor do pagamento", example = "pix", position = 1)
    @NotBlank(message = "valor deve ser preenchido")
    private Double valor;
    @ApiModelProperty(value = "Informacao do numero para pagamento", example = "pix", position = 1)
    @NotBlank(message = "numero para pagamento deve ser preenchido")
    private String numeroParaPagamento;
    @ApiModelProperty(value = "Informacao do data de pagamento", example = "pix", position = 1)
    @NotBlank(message = "data de pagamento deve ser preenchido")
    private LocalDate dataPagamento;

    public PagamentoDTO(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.tipoPagamento = pagamento.getTipoPagamento().name(); // Você pode usar o nome do enum para representar o tipo de pagamento
        this.valor = pagamento.getValor();
        this.numeroParaPagamento = pagamento.getNumeroParaPagamento();
        this.dataPagamento = pagamento.getDataPagamento();
    }
}
