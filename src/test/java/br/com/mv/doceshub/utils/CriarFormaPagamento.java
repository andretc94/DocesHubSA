package br.com.mv.doceshub.utils;

import java.util.ArrayList;

import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.model.Venda;

public class CriarFormaPagamento {
	
	public static FormaPagamento formaPagamentoValida() {
		return FormaPagamento.builder()
				.id(1l)
				.vendas(new ArrayList<Venda>())
				.descricao("DescricaoTeste")
				.build();
	}
	
	public static FormaPagamento formaPagamentoParaSerSalva() {
		return FormaPagamento.builder()
				.descricao("DescricaoTeste")
				.build();
	}
	
	public static FormaPagamento formaPagamentoParaSerAtualizada() {
		return FormaPagamento.builder()
				.id(1l)
				.vendas(new ArrayList<Venda>())
				.descricao("DescricaoTesteAtualizada")
				.build();
	}
	
}
