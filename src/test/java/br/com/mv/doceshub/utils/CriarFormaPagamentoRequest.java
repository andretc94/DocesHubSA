package br.com.mv.doceshub.utils;

import br.com.mv.doceshub.dto.request.formapagamento.FormaPagamentoRequest;

public class CriarFormaPagamentoRequest {

	public static FormaPagamentoRequest criarFormaPagamento() {
		FormaPagamentoRequest formaPagamentoRequest = new FormaPagamentoRequest();
		formaPagamentoRequest.setDescricao(CriarFormaPagamento.formaPagamentoParaSerSalva().getDescricao());
		return formaPagamentoRequest;
	}
	
	public static FormaPagamentoRequest atualizarFormaPagamento() {
		FormaPagamentoRequest formaPagamentoRequest = new FormaPagamentoRequest();
		formaPagamentoRequest.setDescricao(CriarFormaPagamento.formaPagamentoParaSerAtualizada().getDescricao());
		return formaPagamentoRequest;
	}

}