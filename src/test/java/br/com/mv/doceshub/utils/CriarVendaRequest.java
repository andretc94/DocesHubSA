package br.com.mv.doceshub.utils;

import java.math.BigDecimal;

import br.com.mv.doceshub.dto.request.venda.ClienteId;
import br.com.mv.doceshub.dto.request.venda.FormaPagamentoId;
import br.com.mv.doceshub.dto.request.venda.VendaRequest;

public class CriarVendaRequest {
	
	public static VendaRequest criarVendaRequest() {
		return VendaRequest.builder()
				.cliente(ClienteId.builder().id(1l).build())
				.formaPagamento(FormaPagamentoId.builder().id(1l).build())
				.valorPago(BigDecimal.ONE)
				.build();
	}

}
