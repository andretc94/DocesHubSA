package br.com.mv.doceshub.dto.request.venda;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealizarPagamentoRequest {

	@Positive
	private BigDecimal valor;
}
