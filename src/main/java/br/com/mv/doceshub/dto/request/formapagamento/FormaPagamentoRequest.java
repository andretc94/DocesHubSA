package br.com.mv.doceshub.dto.request.formapagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mv.doceshub.model.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoRequest {
	
	@NotNull(message = "a descricao nao pode ser nula")
	@NotBlank(message = "a descricao nao pode ser vazia")
	private String descricao;
	
	
	public static FormaPagamento converter(FormaPagamentoRequest request) {
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDescricao(request.getDescricao());
		return formaPagamento;
	}
	
}
