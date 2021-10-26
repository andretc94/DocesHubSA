package br.com.mv.doceshub.utils;

import java.math.BigDecimal;

import br.com.mv.doceshub.model.TipoDoce;

public class CriarTipoDeDoce {

	public static TipoDoce tipoDeDoceValido() {
		return TipoDoce.builder()
				.id(1l)
				.descricao("tipo de doce teste")
				.qtdEstoque(1)
				.valorUnitario(BigDecimal.valueOf(10))
				.build();
	}
	
	public static TipoDoce tipoDeDoceParaSerSalvo() {
		return TipoDoce.builder()
				.descricao("tipo de doce teste")
				.qtdEstoque(1)
				.valorUnitario(BigDecimal.valueOf(10))
				.build();
	}
	
	public static TipoDoce tipoDeDoceParaAtualizar() {
		return TipoDoce.builder()
				.id(1l)
				.descricao("tipo de doce teste atualizado")
				.qtdEstoque(1)
				.valorUnitario(BigDecimal.valueOf(10))
				.build();
	}
	
}
