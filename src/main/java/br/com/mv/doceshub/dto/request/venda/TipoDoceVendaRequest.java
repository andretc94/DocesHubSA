package br.com.mv.doceshub.dto.request.venda;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDoceVendaRequest {

	@NotBlank
	private Long id;
}
