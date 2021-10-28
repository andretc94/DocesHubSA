package br.com.mv.doceshub.dto.response.formapagamento;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mv.doceshub.model.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoResponse {

	private Long id;
	private String descricao;

	public static FormaPagamentoResponse converter(FormaPagamento formaPagamento) {
		FormaPagamentoResponse response = new FormaPagamentoResponse();
		response.setId(formaPagamento.getId());
		response.setDescricao(formaPagamento.getDescricao());
		return response;
	}

	public static List<FormaPagamentoResponse> converter(List<FormaPagamento> lista) {
		return lista.stream().map(formaPagamento -> FormaPagamentoResponse.converter(formaPagamento)).collect(Collectors.toList());
	}

}