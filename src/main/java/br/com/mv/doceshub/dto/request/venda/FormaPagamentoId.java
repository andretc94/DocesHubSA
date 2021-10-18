package br.com.mv.doceshub.dto.request.venda;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoId {

	@NotNull(message = "informe um id de forma de pagamento valido")
	private Long id;
}
