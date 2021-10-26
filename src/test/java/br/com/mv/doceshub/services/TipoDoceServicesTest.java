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

import br.com.mv.doceshub.exceptions.NegocioException;
import br.com.mv.doceshub.model.TipoDoce;
import br.com.mv.doceshub.repositories.TipoDoceRepository;
import br.com.mv.doceshub.utils.CriarTipoDeDoce;

@ExtendWith(SpringExtension.class)
class TipoDoceServicesTest {
	
	@InjectMocks
	TipoDoceServices tipoDoceServices;
	
	@Mock
	TipoDoceRepository tipoDoceRepositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		List<TipoDoce> lista = List.of(CriarTipoDeDoce.tipoDeDoceValido());
		Optional<TipoDoce> optional = Optional.of(CriarTipoDeDoce.tipoDeDoceValido());

		BDDMockito.when(tipoDoceRepositoryMock.findAll()).thenReturn(lista);

		BDDMockito.when(tipoDoceRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(optional);

		BDDMockito.when(tipoDoceRepositoryMock.findByDescricaoContainingIgnoreCase(ArgumentMatchers.anyString()))
				.thenReturn(lista);

		BDDMockito.when(tipoDoceRepositoryMock.save(ArgumentMatchers.any(TipoDoce.class)))
				.thenReturn(CriarTipoDeDoce.tipoDeDoceValido());
	}

	@Test
	void testSalvar() {
		TipoDoce salvar = tipoDoceServices.salvar(CriarTipoDeDoce.tipoDeDoceParaSerSalvo());
		Assertions.assertThat(salvar).isNotNull();
		Assertions.assertThat(salvar.getId()).isNotNull();
	}

	@Test
	void testListar() {
		String descricao = CriarTipoDeDoce.tipoDeDoceValido().getDescricao();
		List<TipoDoce> listar = tipoDoceServices.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getDescricao()).isEqualTo(descricao);
	}

	@Test
	void testBuscarPorDescricao() {
		List<TipoDoce> buscarPorNome = tipoDoceServices.buscarPorDescricao("a");
		Assertions.assertThat(buscarPorNome).isNotNull();
		Assertions.assertThat(buscarPorNome).isNotEmpty().hasSize(1);
	}

	@Test
	void testBuscar() {
		TipoDoce buscar = tipoDoceServices.buscar(1l);
		Assertions.assertThat(buscar).isNotNull().isEqualTo(CriarTipoDeDoce.tipoDeDoceValido());
	}

	@Test
	void testAtualizar() {
		TipoDoce atualizar = tipoDoceServices.atualizar(1l, CriarTipoDeDoce.tipoDeDoceParaAtualizar());
		Assertions.assertThat(atualizar).isNotNull();
		Assertions.assertThat(atualizar.getDescricao()).isEqualTo(CriarTipoDeDoce.tipoDeDoceValido().getDescricao());
	}

	@Test
	void testAtualizarEstoque_ComSucesso() {
		TipoDoce atualizarEstoque = tipoDoceServices.atualizarEstoque(1l, 1);
		Assertions.assertThat(atualizarEstoque).isNotNull();	
	}
	
	@Test
	void testAtualizarEstoque_ComFalha() {
		Assertions.assertThatCode(()->tipoDoceServices.atualizarEstoque(1l, 5)).isInstanceOf(NegocioException.class);
	}

	@Test
	void testRemover() {
		Assertions.assertThatCode(()->tipoDoceServices.remover(1l)).doesNotThrowAnyException();
	}

}
