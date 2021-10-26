package br.com.mv.doceshub.utils;

import java.math.BigDecimal;

import br.com.mv.doceshub.dto.request.venda.RealizarPagamentoRequest;

public class CriarPagamentoRequest {
	
	public static RealizarPagamentoRequest criarPagamento() {
		return RealizarPagamentoRequest.builder()
				.valor(BigDecimal.ONE)
				.build();
		
	}

}
