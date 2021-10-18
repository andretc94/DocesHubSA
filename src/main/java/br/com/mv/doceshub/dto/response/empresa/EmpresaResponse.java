package br.com.mv.doceshub.dto.response.empresa;

import java.util.List;

import br.com.mv.doceshub.model.Empresa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaResponse {
	
	
	private Long id;
	private String nome; 
	private Boolean ativa;
	
	
	public static EmpresaResponse converter(Empresa empresa) {
		EmpresaResponse response = new EmpresaResponse();
		response.setId(empresa.getId());
		response.setNome(empresa.getNome());
		response.setAtiva(empresa.getAtiva());
		return response;
	}
	
	public static List<EmpresaResponse> converter(List<Empresa> empresas) {
		return empresas.stream().map(empresa->converter(empresa)).toList();
	}

}
