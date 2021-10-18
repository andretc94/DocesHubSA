package br.com.mv.doceshub.dto.response.cliente;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaInadiplenteResponse {
	
	private Long id;
	private BigDecimal valorDevido;
	private BigDecimal valorTotal;
}
