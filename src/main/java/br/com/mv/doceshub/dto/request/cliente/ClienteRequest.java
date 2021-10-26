package br.com.mv.doceshub.dto.request.cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ClienteRequest {
	@NotNull(message = "o nome deve ser informado")
	@NotBlank(message = "o nome nao pode ser vazio")
	private String nome;
	
	@Email(message = "informe um email valido")
	private String email;
	
	@NotNull(message = "o telefone deve ser informado")
	@NotBlank(message = "o telefone nao pode ser vazio")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "O numero deve ter ddd seguido de 8 numeros")
	private String telefone;
	
	@NotNull(message = "o campo empresaId deve ser informado")
	@Positive(message = "informe um id da empresa valido")
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
