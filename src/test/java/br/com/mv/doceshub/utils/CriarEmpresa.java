package br.com.mv.doceshub.utils;

import br.com.mv.doceshub.model.Empresa;

public class CriarEmpresa {
	
	public static Empresa empresaParaSerSalva() {
		return Empresa.builder()
				.nome("Empresa Teste")
				.build();
	}
	
	public static Empresa empresaValida() {
		return Empresa.builder()
				.id(1l)
				.nome("Empresa Teste")
				.build();
	}
	
	public static Empresa empresaParaSerAtualizada() {
		return Empresa.builder()
				.id(1l)
				.nome("Empresa Teste Atualizada")
				.build();
	}

}
