package br.com.mv.doceshub.dto.request.tipodoce;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import br.com.mv.doceshub.model.TipoDoce;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDoceRequest {
	
	@NotNull(message = "descricao nao pode ser nula")
	@NotBlank(message = "descricao nao pode ser vazia")
	private String descricao;
	
	@NotNull(message = "valorUnitario nao pode ser nulo")
	@Positive(message = "o valor unitario deve ver maior que 0")
	private BigDecimal valorUnitario;
	
	@NotNull(message = "quantidade de estoque nao pode ser nula")
	@PositiveOrZero(message = "estoque nao pode ser menor que 0")
	private Integer qtdEstoque;
	
	public static TipoDoce converter(TipoDoceRequest request) {
		TipoDoce novoDoce = new TipoDoce();
		
		novoDoce.setDescricao(request.getDescricao());
		novoDoce.setQtdEstoque(request.getQtdEstoque());
		novoDoce.setValorUnitario(request.getValorUnitario());
		
		return novoDoce;
	}

}
