package br.com.mv.doceshub.utils;

import br.com.mv.doceshub.model.Cliente;

public class CriarCliente {
	
	public static Cliente ClienteValido() {
		return Cliente.builder()
				.id(1l)
				.email("teste@teste.com")
				.empresa(CriarEmpresa.empresaValida())
				.nome("teste cliente")
				.telefone("1234564567")
				.build();
	}
	
	public static Cliente ClienteParaSerSalva() {
		return Cliente.builder()
				.email("teste@teste.com")
				.empresa(CriarEmpresa.empresaValida())
				.nome("teste cliente")
				.telefone("1234564567")
				.build();
	}
	
	public static Cliente ClienteParaSerAtualizada() {
		return Cliente.builder()
				.id(1l)
				.email("teste@teste.com")
				.empresa(CriarEmpresa.empresaValida())
				.nome("teste cliente")
				.telefone("1234564567")
				.build();
	}

}
