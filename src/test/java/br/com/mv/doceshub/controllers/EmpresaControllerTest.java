package br.com.mv.doceshub.controllers;

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

import br.com.mv.doceshub.dto.response.empresa.EmpresaResponse;
import br.com.mv.doceshub.model.Empresa;
import br.com.mv.doceshub.services.EmpresaServices;
import br.com.mv.doceshub.utils.CriarEmpresa;
import br.com.mv.doceshub.utils.CriarEmpresaRequest;

@ExtendWith(SpringExtension.class)
class EmpresaControllerTest {

	@InjectMocks
	EmpresaController empresaController;

	@Mock
	EmpresaServices empresaServicesMock;

	@BeforeEach
	void setUp() {
		List<Empresa> lista = List.of(CriarEmpresa.empresaValida());
		BDDMockito.when(empresaServicesMock.listar()).thenReturn(lista);

		BDDMockito.when(empresaServicesMock.buscarPorNome(ArgumentMatchers.anyString())).thenReturn(lista);

		BDDMockito.when(empresaServicesMock.buscar(ArgumentMatchers.anyLong()))
				.thenReturn(CriarEmpresa.empresaValida());

		BDDMockito.when(empresaServicesMock.salvar(ArgumentMatchers.any(Empresa.class)))
				.thenReturn(CriarEmpresa.empresaValida());

		BDDMockito.when(empresaServicesMock.atualizar(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Empresa.class)))
				.thenReturn(CriarEmpresa.empresaParaSerAtualizada());

		BDDMockito.doNothing().when(empresaServicesMock).remover(ArgumentMatchers.anyLong());
	}

	@Test
	void testListarTodasEmpresas_ComSucesso() {
		List<EmpresaResponse> listar = empresaController.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getNome()).isEqualTo(CriarEmpresa.empresaValida().getNome());
	}

	@Test
	void testBuscarPorNome_ComSucesso() {
		List<EmpresaResponse> buscarPorNome = empresaController.buscarPorNome("empresa");
		Assertions.assertThat(buscarPorNome).isNotNull().isNotEmpty().hasSize(1);
		Assertions.assertThat(buscarPorNome.get(0).getNome()).isEqualTo(CriarEmpresa.empresaValida().getNome());
	}

	@Test
	void testBuscarEmpresaPeloId_ComSucesso() {
		EmpresaResponse empresa = empresaController.buscar(1l);
		Assertions.assertThat(empresa).isNotNull();
		Assertions.assertThat(empresa.getId()).isEqualTo(CriarEmpresa.empresaValida().getId());
	}

	@Test
	void testSalvarUmaEmpresa_ComSucesso() {
		EmpresaResponse salva = empresaController.salvar(CriarEmpresaRequest.criarEmpresa());
		Assertions.assertThat(salva).isNotNull();
		Assertions.assertThat(salva.getNome()).isEqualTo(CriarEmpresaRequest.criarEmpresa().getNome());
	}

	@Test
	void testAtualizarUmaEmpresa_ComSucesso() {
		EmpresaResponse atualizar = empresaController.atualizar(1l, CriarEmpresaRequest.atualizarEmpresa());
		Assertions.assertThat(atualizar).isNotNull();
		Assertions.assertThat(atualizar.getNome()).isEqualTo(CriarEmpresaRequest.atualizarEmpresa().getNome());
	}

	@Test
	void testDeletarEmpresa_ComSucesso() {
		Assertions.assertThatCode(()-> empresaController.deletar(1l)).doesNotThrowAnyException();
		ResponseEntity<?> entity = empresaController.deletar(1l);
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
