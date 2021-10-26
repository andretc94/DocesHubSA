package br.com.mv.doceshub.dto.request.tipodoce;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import br.com.mv.doceshub.model.TipoDoce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDoceRequest {
	
	@NotNull(message = "descricao deve ser informada")
	@NotBlank(message = "descricao nao pode ser vazia")
	private String descricao;
	
	@NotNull(message = "o valor unitario deve ser informado")
	@Positive(message = "o valor unitario deve ver maior que R$0,00")
	private BigDecimal valorUnitario;
	
	@NotNull(message = "a quantidade de estoque deve ser informada")
	@PositiveOrZero(message = "o estoque nao pode ser menor que 0")
	private Integer qtdEstoque;
	
	public static TipoDoce converter(TipoDoceRequest request) {
		TipoDoce novoDoce = new TipoDoce();
		
		novoDoce.setDescricao(request.getDescricao());
		novoDoce.setQtdEstoque(request.getQtdEstoque());
		novoDoce.setValorUnitario(request.getValorUnitario());
		
		return novoDoce;
	}

}
