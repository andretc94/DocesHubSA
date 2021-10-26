package br.com.mv.doceshub.utils;

import br.com.mv.doceshub.dto.request.empresa.EmpresaRequest;

public class CriarEmpresaRequest {
	
	public static EmpresaRequest criarEmpresa() {
		return EmpresaRequest.builder()
				.nome(CriarEmpresa.empresaValida().getNome())
				.ativa(CriarEmpresa.empresaValida().getAtiva())
				.build();
	}
	
	public static EmpresaRequest atualizarEmpresa() {
		return EmpresaRequest.builder()
				.nome(CriarEmpresa.empresaParaSerAtualizada().getNome())
				.ativa(CriarEmpresa.empresaParaSerAtualizada().getAtiva())
				.build();
	}

}
