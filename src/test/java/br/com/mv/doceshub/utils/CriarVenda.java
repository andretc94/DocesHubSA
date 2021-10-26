package br.com.mv.doceshub.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.mv.doceshub.model.ItensVenda;
import br.com.mv.doceshub.model.Venda;

public class CriarVenda {

	public static Venda vendaValida() {
		return Venda.builder()
				.Id(1l)
				.valorPago(BigDecimal.valueOf(5))
				.cliente(CriarCliente.ClienteValido())
				.formaPagamento(CriarFormaPagamento.formaPagamentoValida())
				.itens(List.of(ItensVenda.builder().id(1l).quantidade(1).tipoDeDoce(CriarTipoDeDoce.tipoDeDoceValido())
						.build()))
				.valorTotal(BigDecimal.valueOf(10))
				.pago(true)
				.dataCompra(LocalDateTime.parse("2007-12-03T10:15:30"))
				.dataPagamento(LocalDateTime.parse("2007-12-03T10:15:31")).build();
	}

	public static Venda criaVenda() {
		List<ItensVenda> list = List.of(ItensVenda.builder().id(1l).quantidade(1).tipoDeDoce(CriarTipoDeDoce.tipoDeDoceValido())
				.build());
		
		return Venda.builder()
				.valorPago(BigDecimal.valueOf(5))
				.cliente(CriarCliente.ClienteValido())
				.formaPagamento(CriarFormaPagamento.formaPagamentoValida())
				.itens(list)
				.valorTotal(BigDecimal.valueOf(10))
				.pago(true)
				.dataCompra(LocalDateTime.parse("2007-12-03T10:15:30"))
				.dataPagamento(LocalDateTime.parse("2007-12-03T10:15:31")).build();
	}

}
