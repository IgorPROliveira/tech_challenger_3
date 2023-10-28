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


    public PagamentoDTO(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.tipoPagamento = pagamento.getTipoPagamento().name(); // Você pode usar o nome do enum para representar o tipo de pagamento

    }
}
