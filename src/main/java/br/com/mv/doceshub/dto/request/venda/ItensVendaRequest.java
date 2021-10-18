package br.com.mv.doceshub.dto.request.venda;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItensVendaRequest {
	@NotNull
	@NotEmpty
	private Integer quantidade;
	@NotNull
	@NotEmpty
	private Long idTipoDoce;
	
}
