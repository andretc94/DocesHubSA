package br.com.mv.doceshub.dto.response.cliente;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mv.doceshub.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInadimplenteResponse {

	private Long id;
	private String nome;
	private String telefone;
	private List<VendaInadiplenteResponse> vendasDevidas;

	public static List<ClienteInadimplenteResponse> converter(List<Cliente> clientes) {
		return clientes.stream().map(cliente -> {
			ClienteInadimplenteResponse clienteResponse = new ClienteInadimplenteResponse();
			clienteResponse.setId(cliente.getId());
			clienteResponse.setNome(cliente.getNome());
			clienteResponse.setTelefone(cliente.getTelefone());
			EmpresaClienteResposta empresa = new EmpresaClienteResposta();
			empresa.setId(cliente.getEmpresa().getId());
			empresa.setNome(cliente.getEmpresa().getNome());
			clienteResponse.setVendasDevidas(cliente.getVendas().stream().map(venda -> {
				VendaInadiplenteResponse vendaResponse = new VendaInadiplenteResponse();
				vendaResponse.setId(venda.getId());
				vendaResponse.setValorDevido(venda.getValorTotal().subtract(venda.getValorPago()));
				vendaResponse.setValorTotal(venda.getValorTotal());
				return vendaResponse;
			}).collect(Collectors.toList()));
			return clienteResponse;
		}).toList();
	}

}
