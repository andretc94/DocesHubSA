package br.com.mv.doceshub.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.doceshub.dto.request.venda.RealizarPagamentoRequest;
import br.com.mv.doceshub.dto.request.venda.VendaRequest;
import br.com.mv.doceshub.dto.response.venda.VendaResponse;
import br.com.mv.doceshub.model.Venda;
import br.com.mv.doceshub.services.VendaServices;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private VendaServices<Venda> vendasService;

	@GetMapping
	@ApiOperation("Listar todas as Vendas")
	public List<VendaResponse> listar() {
		return VendaResponse.converterLista(vendasService.listar());
	}

	@ApiOperation("Listar todas as Vendas de um Cliente")
	@GetMapping("cliente/{idCliente}")
	public List<VendaResponse> buscarPorCliente(@PathVariable @NotNull Long idCliente) {
		List<Venda> lista = vendasService.buscarPorIdCliente(idCliente);
		return VendaResponse.converterLista(lista);
	}
	@ApiOperation("Listar todas as Vendas pela data de Compra")
	@GetMapping("/data-compra/")
	public List<VendaResponse> buscarPorDataVenda(@RequestParam String data) {
		List<Venda> lista = vendasService.buscarPorDataVenda(converterData(data));
		return VendaResponse.converterLista(lista);
	}

	@ApiOperation("Listar todas as Vendas pela data de Pagamento")
	@GetMapping("/data-pagamento/")
	public List<VendaResponse> buscarPorDataPagamento(@RequestParam String data) {
		List<Venda> lista = vendasService.buscarPorDataPagamento(converterData(data));
		return VendaResponse.converterLista(lista);
	}

	@ApiOperation("Listar todas as Vendas por um Tipo de Doce")
	@GetMapping("/tipo-doce/{idTipoDoce}")
	public List<VendaResponse> buscarPorTipoDoce(@PathVariable Long idTipoDoce) {
		List<Venda> lista = vendasService.buscarPorTipoDoce(idTipoDoce);
		return VendaResponse.converterLista(lista);
	}

	@ApiOperation("Listar todas as Vendas por um tipo de pagamento")
	@GetMapping("/forma-pagamento/{idFormaPagamento}")
	public List<VendaResponse> buscarPorFormaPagamento(@PathVariable @NotNull Long idFormaPagamento) {
		List<Venda> lista = vendasService.buscarPorFormaPagamento(idFormaPagamento);
		return VendaResponse.converterLista(lista);
	}

	@ApiOperation("Realizar um Pagamento de uma Venda")
	@PostMapping("/realizar-pagamento/{idVenda}")
	public ResponseEntity<String> realizarPagamento(@PathVariable @NotNull Long idVenda,
			@RequestBody @Valid RealizarPagamentoRequest valor) {
		vendasService.realizarPagamento(idVenda, valor.getValor());
		return ResponseEntity.ok("Pagamento Realizado com sucesso!");
	}

	@PostMapping
	@ApiOperation("Realizar uma Venda")
	@ResponseStatus(HttpStatus.CREATED)
	public VendaResponse vender(@RequestBody @Valid VendaRequest vendaReq) {
		Venda vender = vendasService.vender(VendaRequest.deReqParaVenda(vendaReq));
		return VendaResponse.converter(vender);
	}
	@ApiOperation("Deletar uma Venda")
	@DeleteMapping("/{idVenda}")
	public ResponseEntity<String> deletar(@PathVariable @NotNull Long idVenda) {
		vendasService.delete(idVenda);
		return ResponseEntity.ok("Venda Excluida com sucesso!");
	}

	private LocalDate converterData(String data) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-yyyy");
		LocalDate localDate = LocalDate.parse(data, format);
		return localDate;
	}
}
