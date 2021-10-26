package br.com.mv.doceshub.dto.request.empresa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mv.doceshub.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaRequest {
	
	@NotNull
	@NotBlank(message = "o nome nao pode ser vazio")
	private String nome; 
	
	@NotNull(message = "informe se a empresa está ativa(true) ou inativa(false)")
	private Boolean ativa;
	
	public static Empresa converter(EmpresaRequest request) {
		Empresa novaEmpresa = new Empresa();
		novaEmpresa.setAtiva(request.getAtiva());
		novaEmpresa.setNome(request.getNome());
		return novaEmpresa;
	}

}
