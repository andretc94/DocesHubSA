package br.com.mv.doceshub.utils;

import br.com.mv.doceshub.dto.request.tipodoce.TipoDoceRequest;

public class CriarTipoDeDoceRequest {

	public static TipoDoceRequest criarTipoDoceRequest(){
		return TipoDoceRequest.builder()
		.descricao(CriarTipoDeDoce.tipoDeDoceParaSerSalvo().getDescricao())
		.qtdEstoque(CriarTipoDeDoce.tipoDeDoceParaSerSalvo().getQtdEstoque())
		.valorUnitario(CriarTipoDeDoce.tipoDeDoceParaSerSalvo().getValorUnitario())
		.build();
	}
	
	public static TipoDoceRequest atualizarTipoDoceRequest(){
		return TipoDoceRequest.builder()
		.descricao(CriarTipoDeDoce.tipoDeDoceParaAtualizar().getDescricao())
		.qtdEstoque(CriarTipoDeDoce.tipoDeDoceParaAtualizar().getQtdEstoque())
		.valorUnitario(CriarTipoDeDoce.tipoDeDoceParaAtualizar().getValorUnitario())
		.build();
	}
}
