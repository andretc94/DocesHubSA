package br.com.mv.doceshub.controllers;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.mv.doceshub.dto.response.venda.VendaResponse;
import br.com.mv.doceshub.model.Venda;
import br.com.mv.doceshub.services.VendaServices;
import br.com.mv.doceshub.utils.CriarPagamentoRequest;
import br.com.mv.doceshub.utils.CriarVenda;
import br.com.mv.doceshub.utils.CriarVendaRequest;

@ExtendWith(SpringExtension.class)
class VendasControllerTest {
	@InjectMocks
	VendasController vendasController;
	@Mock
	VendaServices vendaServicesMock;

	@BeforeEach
	void setUp() throws Exception {
		List<Venda> lista = List.of(CriarVenda.vendaValida());
		BDDMockito.when(vendaServicesMock.listar()).thenReturn(lista);

		BDDMockito.when(vendaServicesMock.buscarPorIdCliente(ArgumentMatchers.anyLong())).thenReturn(lista);

		BDDMockito.when(vendaServicesMock.buscarPorTipoDoce(ArgumentMatchers.anyLong())).thenReturn(lista);

		BDDMockito.when(vendaServicesMock.buscarPorFormaPagamento(ArgumentMatchers.anyLong())).thenReturn(lista);

		BDDMockito.when(vendaServicesMock.buscarPorDataVenda(ArgumentMatchers.any(LocalDate.class))).thenReturn(lista);
		
		BDDMockito.when(vendaServicesMock.buscarPorDataPagamento(ArgumentMatchers.any(LocalDate.class))).thenReturn(lista);
		
		BDDMockito.when(vendaServicesMock.buscarVenda(ArgumentMatchers.anyLong()))
				.thenReturn(CriarVenda.vendaValida());

		BDDMockito.when(vendaServicesMock.vender(ArgumentMatchers.any(Venda.class)))
				.thenReturn(CriarVenda.vendaValida());
	}

	@Test
	void testListar() {
		List<VendaResponse> listar = vendasController.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getCliente()).isEqualTo(CriarVenda.vendaValida().getCliente().getNome());
	}

	@Test
	void testBuscarPorCliente() {
		List<VendaResponse> buscarPorIdCliente = vendasController.buscarPorCliente(1l);
		Assertions.assertThat(buscarPorIdCliente).isNotNull();
		Assertions.assertThat(buscarPorIdCliente).isNotEmpty().hasSize(1);
		Assertions.assertThat(buscarPorIdCliente.get(0).getCliente()).isEqualTo(CriarVenda.vendaValida().getCliente().getNome());
	}

	@Test
	void testBuscarPorDataVenda() {
		List<VendaResponse> buscarPorDataPagamento = vendasController.buscarPorDataVenda("10-10-2021");
		Assertions.assertThat(buscarPorDataPagamento).isNotNull();
		Assertions.assertThat(buscarPorDataPagamento).isNotEmpty().hasSize(1);
		Assertions.assertThat(buscarPorDataPagamento.get(0).getCliente()).isEqualTo(CriarVenda.vendaValida().getCliente().getNome());
	}

	@Test
	void testBuscarPorDataPagamento() {
		List<VendaResponse> buscarPorDataPagamento = vendasController.buscarPorDataPagamento("10-10-2021");
		Assertions.assertThat(buscarPorDataPagamento).isNotNull();
		Assertions.assertThat(buscarPorDataPagamento).isNotEmpty().hasSize(1);
		Assertions.assertThat(buscarPorDataPagamento.get(0).getCliente()).isEqualTo(CriarVenda.vendaValida().getCliente().getNome());
	}

	@Test
	void testBuscarPorTipoDoce() {
		List<VendaResponse> buscarPorTipoDoce = vendasController.buscarPorTipoDoce(1l);
		Assertions.assertThat(buscarPorTipoDoce).isNotNull();
		Assertions.assertThat(buscarPorTipoDoce).isNotEmpty().hasSize(1);
	}

	@Test
	void testBuscarPorFormaPagamento() {
		List<VendaResponse> buscarPorFormaPagamento = vendasController.buscarPorFormaPagamento(1l);
		Assertions.assertThat(buscarPorFormaPagamento).isNotNull();
		Assertions.assertThat(buscarPorFormaPagamento).isNotEmpty().hasSize(1);
		Assertions.assertThat(buscarPorFormaPagamento.get(0).getFormaPagamento()).isEqualTo(CriarVenda.vendaValida().getFormaPagamento().getDescricao());
	}

	@Test
	void testRealizarPagamento() {
		ResponseEntity<String> vendaPaga = vendasController.realizarPagamento(1l, CriarPagamentoRequest.criarPagamento());
		Assertions.assertThat(vendaPaga.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testVender() {
		VendaResponse vendaRealizada = vendasController.vender(CriarVendaRequest.criarVendaRequest());
		Assertions.assertThat(vendaRealizada).isNotNull();
		Assertions.assertThat(vendaRealizada.getId()).isNotNull();
	}

	@Test
	void testDeletar() {
		ResponseEntity<String> deletar = vendasController.deletar(1l);
		Assertions.assertThat(deletar.getStatusCode()).isEqualTo(HttpStatus.OK);
	}


}
