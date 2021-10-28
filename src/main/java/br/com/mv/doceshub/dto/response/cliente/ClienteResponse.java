package br.com.mv.doceshub.dto.response.cliente;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mv.doceshub.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private EmpresaClienteResposta empresa;
	
	public static ClienteResponse converter(Cliente cliente) {
		ClienteResponse resposta = new ClienteResponse();
		resposta.setId(cliente.getId());
		resposta.setNome(cliente.getNome());
		resposta.setTelefone(cliente.getTelefone());
		
		EmpresaClienteResposta empresa = new EmpresaClienteResposta();
		
		empresa.setId(cliente.getEmpresa().getId());
		empresa.setNome(cliente.getEmpresa().getNome());
		
		resposta.setEmpresa(empresa);
		resposta.setEmail(cliente.getEmail());
		
		return resposta;
	}
	
	public static List<ClienteResponse> converter(List<Cliente> clientes) {
		return clientes.stream().map(cliente->ClienteResponse.converter(cliente)).collect(Collectors.toList());
	}
	
}

