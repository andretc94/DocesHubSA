package br.com.mv.doceshub.controllers;

import java.util.Collections;
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

import br.com.mv.doceshub.dto.response.formapagamento.FormaPagamentoResponse;
import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.services.FormaPagamentoServices;
import br.com.mv.doceshub.utils.CriarFormaPagamento;
import br.com.mv.doceshub.utils.CriarFormaPagamentoRequest;

@ExtendWith(SpringExtension.class)
class FormaPagamentoControllerTest {

	@InjectMocks
	FormaPagamentoController formaPagamentoController;

	@Mock
	FormaPagamentoServices formaPagamentoServicesMock;

	@BeforeEach
	void setUp() {
		List<FormaPagamento> lista = List.of(CriarFormaPagamento.formaPagamentoValida());
		BDDMockito.when(formaPagamentoServicesMock.listar()).thenReturn(lista);

		BDDMockito.when(formaPagamentoServicesMock.buscarPorDescricao(ArgumentMatchers.anyString())).thenReturn(lista);

		BDDMockito.when(formaPagamentoServicesMock.buscar(ArgumentMatchers.anyLong()))
				.thenReturn(CriarFormaPagamento.formaPagamentoValida());

		BDDMockito.when(formaPagamentoServicesMock.salvar(ArgumentMatchers.any(FormaPagamento.class)))
				.thenReturn(CriarFormaPagamento.formaPagamentoValida());

		BDDMockito
				.when(formaPagamentoServicesMock.atualizar(ArgumentMatchers.anyLong(),
						ArgumentMatchers.any(CriarFormaPagamento.formaPagamentoParaSerAtualizada().getClass())))
				.thenReturn(CriarFormaPagamento.formaPagamentoParaSerAtualizada());

		BDDMockito.doNothing().when(formaPagamentoServicesMock).remover(ArgumentMatchers.anyLong());
	}

	@Test
	void listar_RetornaUmaListaDeFormaDePagamento_ComSucesso() {
		String descricao = CriarFormaPagamento.formaPagamentoValida().getDescricao();
		List<FormaPagamentoResponse> listar = formaPagamentoController.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getDescricao()).isEqualTo(descricao);
	}

	@Test
	void listar_RetornaUmaListaDeFormaDePagamentoComParteDaDescricao_ComSucesso() {
		List<FormaPagamentoResponse> lista = formaPagamentoController.buscarPorDescricao("a");
		Assertions.assertThat(lista).isNotNull().isNotEmpty().hasSize(1);
	}

	@Test
	void listar_RetornaUmaListaVaziaDeFormaDePagamento_ComParteDaDescricaoNaoEncontrada() {
		BDDMockito.when(formaPagamentoServicesMock.buscarPorDescricao(ArgumentMatchers.anyString()))
				.thenReturn(Collections.emptyList());
		List<FormaPagamentoResponse> lista = formaPagamentoController.buscarPorDescricao("a");
		Assertions.assertThat(lista).isNotNull().isEmpty();
	}

	@Test
	void buscarPeloId_RetornaUmaFormaDePagamento_ComSucesso() {
		FormaPagamentoResponse formaPag = formaPagamentoController.buscar(1L);
		Assertions.assertThat(formaPag).isNotNull();
		Assertions.assertThat(formaPag.getId().compareTo(1l));
	}

	@Test
	void salvar_RetornaUmaFormaDePagamentoSalva_ComSucesso() {
		FormaPagamentoResponse formaPagSalva = formaPagamentoController
				.salvar(CriarFormaPagamentoRequest.criarFormaPagamento());
		Assertions.assertThat(formaPagSalva).isNotNull();
		Assertions.assertThat(formaPagSalva.getDescricao())
				.isEqualTo(CriarFormaPagamentoRequest.criarFormaPagamento().getDescricao());
	}

	@Test
	void atualizar_RetornaUmaFormaDePagamentoAtualizada_ComSucesso() {
		FormaPagamentoResponse atualizar = formaPagamentoController.atualizar(1l,
				CriarFormaPagamentoRequest.atualizarFormaPagamento());
		Assertions.assertThat(atualizar).isNotNull();
		Assertions.assertThat(atualizar.getDescricao())
				.isEqualTo(CriarFormaPagamentoRequest.atualizarFormaPagamento().getDescricao());
	}

	@Test
	void deletar_DeletarFormaDePagamento_ComSucesso() {
		Assertions.assertThatCode(() -> formaPagamentoController.deletar(1l)).doesNotThrowAnyException();
		ResponseEntity<?> entity = formaPagamentoController.deletar(1l);
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
