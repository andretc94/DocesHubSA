package br.com.mv.doceshub.utils;

import br.com.mv.doceshub.dto.request.cliente.ClienteRequest;

public class CriarClienteRequest {
	
	public static ClienteRequest salvarClienteRequest(){
		return ClienteRequest.builder()
		.email(CriarCliente.ClienteParaSerSalva().getEmail())
		.empresaId(CriarCliente.ClienteParaSerSalva().getEmpresa().getId())
		.nome(CriarCliente.ClienteParaSerSalva().getNome())
		.telefone(CriarCliente.ClienteParaSerSalva().getTelefone())
		.build();
	}
	
	public static ClienteRequest atualizarClienteRequest(){
		return ClienteRequest.builder()
		.email(CriarCliente.ClienteParaSerAtualizada().getEmail())
		.empresaId(CriarCliente.ClienteParaSerAtualizada().getEmpresa().getId())
		.nome(CriarCliente.ClienteParaSerAtualizada().getNome())
		.telefone(CriarCliente.ClienteParaSerAtualizada().getTelefone())
		.build();
	}

}
