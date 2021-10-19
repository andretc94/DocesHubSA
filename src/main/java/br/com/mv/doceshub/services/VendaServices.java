package br.com.mv.doceshub.services;

import br.com.mv.doceshub.exceptions.ValorPagoExcedidoException;
import br.com.mv.doceshub.exceptions.VendaNaoEncontradaException;
import br.com.mv.doceshub.exceptions.VendaNaoPodeSerExcluidaException;
import br.com.mv.doceshub.model.*;
import br.com.mv.doceshub.repositories.VendasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VendaServices {

	private final VendasRepository vendasRepository;

	private final ClienteServices clienteService;

	private final TipoDoceServices tipodoceService;

	private final FormaPagamentoServices formaPagamentoService;

	public List<Venda> listar() {
		return vendasRepository.findAll();
	}

	public List<Venda> buscarPorIdCliente(Long idCliente) {
		Cliente cliente = clienteService.buscar(idCliente);
		return vendasRepository.findByCliente(cliente);
	}

	public List<Venda> buscarPorFormaPagamento(Long idFormaPagamento) {
		FormaPagamento formaPag = formaPagamentoService.buscar(idFormaPagamento);
		return vendasRepository.findByFormaPagamento(formaPag);
	}

	public List<Venda> buscarPorDataPagamento(LocalDate data) {
		return vendasRepository.findByDataPagamentoBetween(data.atStartOfDay(), data.atTime(LocalTime.MAX));
	}

	public List<Venda> buscarPorDataVenda(LocalDate data) {
		return vendasRepository.findByDataCompraBetween(data.atStartOfDay(), data.atTime(LocalTime.MAX));
	}

	public List<Venda> buscarPorTipoDoce(Long idTipoDoce) {
		TipoDoce tipoDoce = tipodoceService.buscar(idTipoDoce);
		return vendasRepository.findByTipoDeDoce(tipoDoce);
	}

	@Transactional
	public Venda vender(Venda novaVenda) {
		Long idCliente = novaVenda.getCliente().getId();
		novaVenda.setCliente(clienteService.buscar(idCliente));

		Long idFormaPagamento = novaVenda.getFormaPagamento().getId();
		novaVenda.setFormaPagamento(formaPagamentoService.buscar(idFormaPagamento));

		List<ItensVenda> itensVenda = novaVenda.getItens();
		BigDecimal valorPago = novaVenda.getValorPago();

		BigDecimal totalCompra = itensVenda.stream().map(item -> {
			var idTipoDoce = item.getTipoDeDoce().getId();
			TipoDoce tipoDoce = tipodoceService.atualizarEstoque(idTipoDoce, item.getQuantidade());

			item.setTipoDeDoce(tipoDoce);

			return tipoDoce.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));

		}).reduce(BigDecimal.ZERO,BigDecimal::add);

		novaVenda.setValorTotal(totalCompra);

		int compare = valorPago.compareTo(totalCompra);
		//pago menor total
		if (compare == -1) {
			novaVenda.setPago(false);
		} else if (compare == 1) { // pago maior que o total
			throw new ValorPagoExcedidoException("A valor pago nao pode superior ao total da compra!");
		} else {
			novaVenda.setPago(true);
			novaVenda.setDataPagamento(LocalDateTime.now());
		}
		novaVenda.setDataCompra(LocalDateTime.now());

		return vendasRepository.save(novaVenda);
	}

	public Venda realizarPagamento(Long idVenda, BigDecimal valorRecebido) {
		Venda venda = buscarVenda(idVenda);

		BigDecimal totalDevido = venda.getValorTotal().subtract(venda.getValorPago());

		var compare = valorRecebido.compareTo(totalDevido);

		if (compare == 1) {
			throw new ValorPagoExcedidoException(String.format(
					"A valor recebido R$ %.2f nao pode superior ao total devido da compra é R$ %.2f",
					valorRecebido,
					totalDevido));
		}
		
		if (compare == -1) {
			venda.setValorPago(venda.getValorPago().add(valorRecebido));
			venda.setDataPagamento(LocalDateTime.now());
			return vendasRepository.save(venda);
		}
		
		if (compare == 0) {
			venda.setValorPago(venda.getValorPago().add(valorRecebido));
			venda.setPago(true);
			venda.setDataPagamento(LocalDateTime.now());
			return vendasRepository.save(venda);
		}

		return vendasRepository.save(venda);
	}

	public Venda buscarVenda(Long idVenda) {
		return vendasRepository.findById(idVenda).orElseThrow(() -> new VendaNaoEncontradaException(idVenda));
	}

	public void delete(Long idVenda) {
		System.out.println(idVenda);
		Venda venda = this.buscarVenda(idVenda);
		
		if(venda.getPago()) {
			vendasRepository.delete(venda);
		}else {
			throw new VendaNaoPodeSerExcluidaException(idVenda);
		}
		
	}

}
