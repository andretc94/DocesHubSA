package br.com.mv.doceshub.controllers;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.mv.doceshub.dto.response.formapagamento.FormaPagamentoResponse;
import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.services.FormaPagamentoServices;
import br.com.mv.doceshub.utils.CriarFormaPagamento;

@ExtendWith(SpringExtension.class)
class FormaPagamentoControllerTest {
	
	@InjectMocks
	FormaPagamentoController formaPagamentoController;
	
	@Mock
	FormaPagamentoServices formaPagamentoServicesMock;
	
	@BeforeEach
	void setUp() {
		List<FormaPagamento> lista = List.of(CriarFormaPagamento.formaPagamentoValida());
		BDDMockito.when(formaPagamentoServicesMock.listar())
			.thenReturn(lista);
		System.out.println(lista);
	}
	
	@Test
	void listar_RetornaUmaListaDeFormaDePagamento_ComSucesso() {
		String descricao = CriarFormaPagamento.formaPagamentoValida().getDescricao();
		List<FormaPagamentoResponse> listar = formaPagamentoController.listar();
		
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getDescricao()).isEqualTo(descricao);
	}


}
