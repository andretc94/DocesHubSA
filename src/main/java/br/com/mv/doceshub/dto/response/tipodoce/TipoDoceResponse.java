package br.com.mv.doceshub.dto.response.tipodoce;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mv.doceshub.model.TipoDoce;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDoceResponse {

	private Long id;
	private String descricao;
	private BigDecimal valorUnitario;
	private Integer qtdEstoque;
	
	public static TipoDoceResponse converter(TipoDoce doce) {
		TipoDoceResponse response = new TipoDoceResponse();
		
		response.setId(doce.getId());
		response.setDescricao(doce.getDescricao());
		response.setQtdEstoque(doce.getQtdEstoque());
		response.setValorUnitario(doce.getValorUnitario());
		return response;
	}
	
	public static List<TipoDoceResponse> converter(List<TipoDoce> doces) {
		return doces.stream().map(doce->TipoDoceResponse.converter(doce)).collect(Collectors.toList());
	}
}
