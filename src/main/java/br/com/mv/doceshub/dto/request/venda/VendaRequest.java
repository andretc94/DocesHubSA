package br.com.mv.doceshub.dto.request.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.model.ItensVenda;
import br.com.mv.doceshub.model.Venda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaRequest {

	@PositiveOrZero(message = "o valor pago deve ser maior ou igual a 0.00")
	private BigDecimal valorPago;
	
	@NotNull
	@NotEmpty(message = "informe uma lista de doces e quantidade valida")
	private List<ItensVendaRequest> itens = new ArrayList<ItensVendaRequest>();
	
	@Valid
	private ClienteId cliente;
	
	@Valid
	private FormaPagamentoId formaPagamento;

	public static Venda deReqParaVenda(VendaRequest vendaReq) {
		Venda venda = new Venda();
		Cliente cliente = new Cliente();
		cliente.setId(vendaReq.getCliente().getId());
		venda.setCliente(cliente);

		FormaPagamento formaPagamento = FormaPagamento.builder()
				.id(vendaReq.getFormaPagamento().getId())
				.build();
		
		venda.setFormaPagamento(formaPagamento);

		venda.setValorPago(vendaReq.getValorPago());

		List<ItensVenda> collect = vendaReq.getItens().stream().map(item -> {
			ItensVenda itemVenda = new ItensVenda();

			Integer quantidade = item.getQuantidade();
			Long tipoDoceId = item.getIdTipoDoce();

			itemVenda.setQuantidade(quantidade);
			itemVenda.getTipoDeDoce().setId(tipoDoceId);
			return itemVenda;
		}).toList();

		venda.setItens(collect);
		return venda;
	}
}
