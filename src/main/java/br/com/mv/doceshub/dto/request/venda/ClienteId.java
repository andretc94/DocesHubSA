package br.com.mv.doceshub.dto.request.venda;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteId {
	
	@NotNull(message = "informe um id de cliente valido")
	private Long id;
}