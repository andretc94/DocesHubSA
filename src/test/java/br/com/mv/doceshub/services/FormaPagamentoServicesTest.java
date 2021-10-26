package br.com.mv.doceshub.services;

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

import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.repositories.FormaPagamantoRepository;
import br.com.mv.doceshub.utils.CriarFormaPagamento;

@ExtendWith(SpringExtension.class)
class FormaPagamentoServicesTest {

	@InjectMocks
	FormaPagamentoServices formaPagamentoServices;
	
	@Mock
	FormaPagamantoRepository formPagamentoRepositoryMock;
	
	@BeforeEach
	void setUp() throws Exception {
		List<FormaPagamento> lista = List.of(CriarFormaPagamento.formaPagamentoValida());
		Optional<FormaPagamento> optional = Optional.of(CriarFormaPagamento.formaPagamentoValida());

		BDDMockito.when(formPagamentoRepositoryMock.findAll()).thenReturn(lista);

		BDDMockito.when(formPagamentoRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(optional);

		BDDMockito.when(formPagamentoRepositoryMock.findByDescricaoContainingIgnoreCase(ArgumentMatchers.anyString()))
				.thenReturn(lista);

		BDDMockito.when(formPagamentoRepositoryMock.save(ArgumentMatchers.any(FormaPagamento.class)))
				.thenReturn(CriarFormaPagamento.formaPagamentoValida());
		
	}

	@Test
	void testListar() {
		String descricao = CriarFormaPagamento.formaPagamentoValida().getDescricao();
		List<FormaPagamento> listar = formaPagamentoServices.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getDescricao()).isEqualTo(descricao);
	}

	@Test
	void testBuscar() {
		FormaPagamento buscar = formaPagamentoServices.buscar(1l);
		Assertions.assertThat(buscar).isNotNull().isEqualTo(CriarFormaPagamento.formaPagamentoValida());
	}

	@Test
	void testBuscarPorDescricao() {
		List<FormaPagamento> buscarPorNome = formaPagamentoServices.buscarPorDescricao("a");
		Assertions.assertThat(buscarPorNome).isNotNull();
		Assertions.assertThat(buscarPorNome).isNotEmpty().hasSize(1);
	}

	@Test
	void testSalvar() {
		FormaPagamento salvar = formaPagamentoServices.salvar(CriarFormaPagamento.formaPagamentoParaSerSalva());
		Assertions.assertThat(salvar).isNotNull();
		Assertions.assertThat(salvar.getId()).isNotNull();
	}

	@Test
	void testAtualizar() {
		FormaPagamento atualizar = formaPagamentoServices.atualizar(1l, CriarFormaPagamento.formaPagamentoParaSerAtualizada());
		Assertions.assertThat(atualizar).isNotNull();
		Assertions.assertThat(atualizar.getDescricao()).isEqualTo(CriarFormaPagamento.formaPagamentoValida().getDescricao());
	}

	@Test
	void testRemover() {
		Assertions.assertThatCode(()->formaPagamentoServices.remover(1l)).doesNotThrowAnyException();
	}

}
