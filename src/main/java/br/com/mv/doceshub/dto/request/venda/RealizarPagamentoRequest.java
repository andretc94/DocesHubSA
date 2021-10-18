package br.com.mv.doceshub.dto.request.venda;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealizarPagamentoRequest {

	@Positive
	private BigDecimal valor;
}
