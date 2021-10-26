package br.com.mv.doceshub.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.model.TipoDoce;
import br.com.mv.doceshub.model.Venda;
import br.com.mv.doceshub.repositories.VendasRepository;
import br.com.mv.doceshub.utils.CriarCliente;
import br.com.mv.doceshub.utils.CriarFormaPagamento;
import br.com.mv.doceshub.utils.CriarTipoDeDoce;
import br.com.mv.doceshub.utils.CriarVenda;

@ExtendWith(SpringExtension.class)
class VendaServicesTest {
	
	@InjectMocks
	VendaServices vendaServices;
	
	@Mock
	ClienteServices clienteService;

	@Mock
	TipoDoceServices tipodoceService;

	@Mock
	FormaPagamentoServices formaPagamentoService;
	
	@Mock
	VendasRepository vendasRepositoryMock;
	

	@BeforeEach
	void setUp() throws Exception {
		List<Venda> lista = List.of(CriarVenda.vendaValida());
		Optional<Venda> optional = Optional.of(CriarVenda.vendaValida());

		BDDMockito.when(vendasRepositoryMock.findAll()).thenReturn(lista);

		BDDMockito.when(vendasRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(optional);
		
		BDDMockito.when(vendasRepositoryMock.findByCliente(ArgumentMatchers.any(Cliente.class))).thenReturn(lista);
		
		BDDMockito.when(clienteService.buscar(ArgumentMatchers.anyLong())).thenReturn(CriarCliente.ClienteValido());
		
		BDDMockito.when(vendasRepositoryMock.findByFormaPagamento(ArgumentMatchers.any(FormaPagamento.class))).thenReturn(lista);

		BDDMockito.when(formaPagamentoService.buscar(ArgumentMatchers.anyLong())).thenReturn(CriarFormaPagamento.formaPagamentoValida());
		
		BDDMockito.when(vendasRepositoryMock.findByDataPagamentoBetween(ArgumentMatchers.any(LocalDateTime.class), ArgumentMatchers.any(LocalDateTime.class))).thenReturn(lista);

		BDDMockito.when(vendasRepositoryMock.findByDataCompraBetween(ArgumentMatchers.any(LocalDateTime.class), ArgumentMatchers.any(LocalDateTime.class))).thenReturn(lista);

		BDDMockito.when(vendasRepositoryMock.findByTipoDeDoce(ArgumentMatchers.any(TipoDoce.class))).thenReturn(lista);

		BDDMockito.when(tipodoceService.buscar(ArgumentMatchers.anyLong())).thenReturn(CriarTipoDeDoce.tipoDeDoceValido());
		
		BDDMockito.when(tipodoceService.atualizarEstoque(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt())).thenReturn(CriarTipoDeDoce.tipoDeDoceValido());

		BDDMockito.when(vendasRepositoryMock.save(ArgumentMatchers.any(Venda.class)))
				.thenReturn(CriarVenda.vendaValida());
		
		BDDMockito.when(vendasRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(optional);

	}

	@Test
	void testListar() {
		List<Venda> listar = vendaServices.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
	}

	@Test
	void testBuscarPorIdCliente() {
		List<Venda> buscarPorIdCliente = vendaServices.buscarPorIdCliente(1l);
		Assertions.assertThat(buscarPorIdCliente).isNotNull();
		Assertions.assertThat(buscarPorIdCliente.get(0).getCliente()).isEqualTo(CriarCliente.ClienteValido());
	}

	@Test
	void testBuscarPorFormaPagamento() {
		List<Venda> buscarPorFormaPagamento = vendaServices.buscarPorFormaPagamento(1l);
		Assertions.assertThat(buscarPorFormaPagamento).isNotNull();
		Assertions.assertThat(buscarPorFormaPagamento.get(0).getFormaPagamento()).isEqualTo(CriarFormaPagamento.formaPagamentoValida());
	}

	@Test
	void testBuscarPorDataPagamento() {
		List<Venda> buscarPorDataPagamento = vendaServices.buscarPorDataPagamento(LocalDate.now());
		Assertions.assertThat(buscarPorDataPagamento).isNotNull();
		Assertions.assertThat(buscarPorDataPagamento).isNotEmpty();
	}

	@Test
	void testBuscarPorDataVenda() {
		List<Venda> buscarPorDataVenda = vendaServices.buscarPorDataVenda(LocalDate.now());
		Assertions.assertThat(buscarPorDataVenda).isNotNull();
		Assertions.assertThat(buscarPorDataVenda).isNotEmpty();
	}

	@Test
	void testBuscarPorTipoDoce() {
		List<Venda> buscarPorTipoDoce = vendaServices.buscarPorTipoDoce(1l);
		Assertions.assertThat(buscarPorTipoDoce).isNotNull();
		Assertions.assertThat(buscarPorTipoDoce.get(0).getItens().get(0).getTipoDeDoce()).isEqualTo(CriarTipoDeDoce.tipoDeDoceValido());
	}

	@Test
	void testVender() {
		Venda vender = vendaServices.vender(CriarVenda.criaVenda());
		Assertions.assertThat(vender).isNotNull();
		Assertions.assertThat(vender.getId()).isNotNull();
	}

	@Test
	void testRealizarPagamento() {
		Venda realizarPagamento = vendaServices.realizarPagamento(1l, BigDecimal.ONE);
		Assertions.assertThat(realizarPagamento).isNotNull();
		Assertions.assertThat(realizarPagamento.getDataPagamento()).isNotNull();
	}

	@Test
	void testBuscarVenda() {
		Venda buscarVenda = vendaServices.buscarVenda(1l);
		Assertions.assertThat(buscarVenda).isNotNull();
	}

	@Test
	void testDelete() {
		Assertions.assertThatCode(()->vendaServices.delete(1l)).doesNotThrowAnyException();
	}

}
