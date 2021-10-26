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

import br.com.mv.doceshub.dto.response.tipodoce.TipoDoceResponse;
import br.com.mv.doceshub.model.TipoDoce;
import br.com.mv.doceshub.services.TipoDoceServices;
import br.com.mv.doceshub.utils.CriarTipoDeDoce;
import br.com.mv.doceshub.utils.CriarTipoDeDoceRequest;

@ExtendWith(SpringExtension.class)
class TipoDoceControllerTest {

	@InjectMocks
	TipoDoceController tipoDoceController;

	@Mock
	TipoDoceServices tipoDoceServicesMock;

	@BeforeEach
	void setUp() {
		List<TipoDoce> lista = List.of(CriarTipoDeDoce.tipoDeDoceValido());
		BDDMockito.when(tipoDoceServicesMock.listar()).thenReturn(lista);

		BDDMockito.when(tipoDoceServicesMock.buscarPorDescricao(ArgumentMatchers.anyString())).thenReturn(lista);

		BDDMockito.when(tipoDoceServicesMock.buscar(ArgumentMatchers.anyLong()))
				.thenReturn(CriarTipoDeDoce.tipoDeDoceValido());

		BDDMockito.when(tipoDoceServicesMock.salvar(ArgumentMatchers.any(TipoDoce.class)))
				.thenReturn(CriarTipoDeDoce.tipoDeDoceValido());

		BDDMockito
				.when(tipoDoceServicesMock.atualizar(ArgumentMatchers.anyLong(),
						ArgumentMatchers.any(CriarTipoDeDoce.tipoDeDoceParaAtualizar().getClass())))
				.thenReturn(CriarTipoDeDoce.tipoDeDoceParaAtualizar());

		BDDMockito.doNothing().when(tipoDoceServicesMock).remover(ArgumentMatchers.anyLong());
	}

	@Test
	void listar_RetornaUmaListaDeFormaDePagamento_ComSucesso() {
		String descricao = CriarTipoDeDoce.tipoDeDoceValido().getDescricao();
		List<TipoDoceResponse> listar = tipoDoceController.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getDescricao()).isEqualTo(descricao);
	}

	@Test
	void listar_RetornaUmaListaDeFormaDePagamentoComParteDaDescricao_ComSucesso() {
		List<TipoDoceResponse> lista = tipoDoceController.listar("a");
		Assertions.assertThat(lista).isNotNull().isNotEmpty().hasSize(1);
	}

	@Test
	void listar_RetornaUmaListaVaziaDeFormaDePagamento_ComParteDaDescricaoNaoEncontrada() {
		BDDMockito.when(tipoDoceServicesMock.buscarPorDescricao(ArgumentMatchers.anyString()))
				.thenReturn(Collections.emptyList());
		List<TipoDoceResponse> lista = tipoDoceController.listar("a");
		Assertions.assertThat(lista).isNotNull().isEmpty();
	}

	@Test
	void buscarPeloId_RetornaUmaFormaDePagamento_ComSucesso() {
		TipoDoceResponse formaPag = tipoDoceController.buscar(1L);
		Assertions.assertThat(formaPag).isNotNull();
		Assertions.assertThat(formaPag.getId().compareTo(1l));
	}

	@Test
	void salvar_RetornaUmaFormaDePagamentoSalva_ComSucesso() {
		TipoDoceResponse tipoDoceSalvo = tipoDoceController
				.salvar(CriarTipoDeDoceRequest.criarTipoDoceRequest());
		Assertions.assertThat(tipoDoceSalvo).isNotNull();
		Assertions.assertThat(tipoDoceSalvo.getDescricao())
				.isEqualTo(CriarTipoDeDoceRequest.criarTipoDoceRequest().getDescricao());
	}

	@Test
	void atualizar_RetornaUmaFormaDePagamentoAtualizada_ComSucesso() {
		TipoDoceResponse atualizar = tipoDoceController.atualizar(CriarTipoDeDoceRequest.atualizarTipoDoceRequest(), 1l);
		Assertions.assertThat(atualizar).isNotNull();
		Assertions.assertThat(atualizar.getDescricao()).isEqualTo(CriarTipoDeDoceRequest.atualizarTipoDoceRequest().getDescricao());
	}
	
	@Test
	void deletar_DeletarFormaDePagamento_ComSucesso() {
		Assertions.assertThatCode(() -> tipoDoceController.delete(1l)).doesNotThrowAnyException();
		ResponseEntity<?> entity = tipoDoceController.delete(1l);
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
