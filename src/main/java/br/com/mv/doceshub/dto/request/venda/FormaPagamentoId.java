package br.com.mv.doceshub.dto.request.venda;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormaPagamentoId {

	@NotNull(message = "informe um id de forma de pagamento valido")
	private Long id;
}
