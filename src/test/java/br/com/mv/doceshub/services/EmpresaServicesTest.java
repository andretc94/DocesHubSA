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

import br.com.mv.doceshub.model.Empresa;
import br.com.mv.doceshub.repositories.EmpresaRepository;
import br.com.mv.doceshub.utils.CriarEmpresa;

@ExtendWith(SpringExtension.class)
class EmpresaServicesTest {

	@InjectMocks
	EmpresaServices empresaServices;

	@Mock
	EmpresaRepository empresaRepositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		List<Empresa> lista = List.of(CriarEmpresa.empresaValida());
		Optional<Empresa> optional = Optional.of(CriarEmpresa.empresaValida());

		BDDMockito.when(empresaRepositoryMock.findAll()).thenReturn(lista);

		BDDMockito.when(empresaRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(optional);

		BDDMockito.when(empresaRepositoryMock.findByNomeContainingIgnoreCase(ArgumentMatchers.anyString()))
				.thenReturn(lista);

		BDDMockito.when(empresaRepositoryMock.save(ArgumentMatchers.any(Empresa.class)))
				.thenReturn(CriarEmpresa.empresaValida());
	}

	@Test
	void testListar() {
		String nome = CriarEmpresa.empresaValida().getNome();
		List<Empresa> listar = empresaServices.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getNome()).isEqualTo(nome);
	}

	@Test
	void testBuscar() {
		Empresa buscar = empresaServices.buscar(1l);
		Assertions.assertThat(buscar).isNotNull().isEqualTo(CriarEmpresa.empresaValida());
	}

	@Test
	void testBuscarPorNome() {
		List<Empresa> buscarPorNome = empresaServices.buscarPorNome("a");
		Assertions.assertThat(buscarPorNome).isNotNull();
		Assertions.assertThat(buscarPorNome).isNotEmpty().hasSize(1);
	}

	@Test
	void testSalvar() {
		Empresa salvar = empresaServices.salvar(CriarEmpresa.empresaParaSerSalva());
		Assertions.assertThat(salvar).isNotNull();
		Assertions.assertThat(salvar.getId()).isNotNull();
	}

	@Test
	void testAtualizar() {
		Empresa atualizar = empresaServices.atualizar(1l, CriarEmpresa.empresaParaSerAtualizada());
		Assertions.assertThat(atualizar).isNotNull();
		Assertions.assertThat(atualizar.getNome()).isEqualTo(CriarEmpresa.empresaValida().getNome());
	}

	@Test
	void testRemover() {
		Assertions.assertThatCode(()->empresaServices.remover(1l)).doesNotThrowAnyException();
	}

}
