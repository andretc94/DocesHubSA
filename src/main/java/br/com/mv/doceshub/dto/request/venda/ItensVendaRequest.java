package br.com.mv.doceshub.dto.request.venda;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItensVendaRequest {
	@NotNull
	@NotEmpty
	private Integer quantidade;
	@NotNull
	@NotEmpty
	private Long idTipoDoce;
	
}
