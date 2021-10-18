package br.com.mv.doceshub.dto.response.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.mv.doceshub.model.Venda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class VendaResponse {
	private Long id;
	private BigDecimal valorPago;
	private BigDecimal valorTotal;
	private List<ItensVendaResponse> tiposDeDoce = new ArrayList<>();
	private String cliente;
	private String formaPagamento;
	private Boolean pago;
	private String dataCompra;
	private String dataPagamento;

	public static VendaResponse converter(Venda venda) {
		VendaResponse resposta = new VendaResponse();
		resposta.setId(venda.getId());
		resposta.setValorPago(venda.getValorPago());
		resposta.setValorTotal(venda.getValorTotal());
		resposta.setDataCompra(dataFormatada(venda.getDataCompra()));
		
		if(venda.getDataPagamento()!=null) {		
			resposta.setDataPagamento(dataFormatada(venda.getDataPagamento()));
		}
		
		List<ItensVendaResponse> tiposDeDoce = new ArrayList<>();

		venda.getItens().forEach(i -> {
			ItensVendaResponse item = new ItensVendaResponse();
			item.setQuantidade(i.getQuantidade());
			item.setDoce(i.getTipoDeDoce().getDescricao());
			tiposDeDoce.add(item);
		});
		resposta.setTiposDeDoce(tiposDeDoce);
		resposta.setCliente(venda.getCliente().getNome());
		resposta.setFormaPagamento(venda.getFormaPagamento().getDescricao());
		resposta.setPago(venda.getPago());
		return resposta;
	}

	private static String dataFormatada(LocalDateTime data) {
		return data.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public static List<VendaResponse> converterLista(List<Venda> listar) {
		return listar.stream().map(i ->converter(i)).collect(Collectors.toList());
	}
}
