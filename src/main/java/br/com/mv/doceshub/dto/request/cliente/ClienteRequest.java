package br.com.mv.doceshub.dto.request.cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.model.Empresa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {
	@NotNull(message = "o nome nao pode ser nulo")
	@NotBlank(message = "o nome nao pode ser vazio")
	private String nome;
	
	@Email(message = "informe um email valido")
	private String email;
	
	@NotBlank(message = "o telefone nao pode ser vazio")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "O numero deve ter ddd seguido de 8 numeros")
	private String telefone;
	
	@NotNull(message = "o campo empresaId nao pode ser nulo")
	@Min(message = "informe um numero de id da empresa valido", value = 1)
	private Long empresaId;
	
	
	public static Cliente converter(ClienteRequest request) {
		Cliente cliente = new Cliente();
		Empresa empresa = new Empresa();
		empresa.setId(request.getEmpresaId());
		
		cliente.setNome(request.getNome());
		cliente.setEmail(request.getEmail());
		cliente.setEmpresa(empresa);
		cliente.setTelefone(request.getTelefone());
		
		return cliente;
	}
	
}
