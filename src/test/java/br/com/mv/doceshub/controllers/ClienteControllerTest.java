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

import br.com.mv.doceshub.dto.response.cliente.ClienteResponse;
import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.services.ClienteServices;
import br.com.mv.doceshub.utils.CriarCliente;
import br.com.mv.doceshub.utils.CriarClienteRequest;

@ExtendWith(SpringExtension.class)
class ClienteControllerTest {
	@InjectMocks
	ClienteController clienteController;

	@Mock
	ClienteServices clienteServicesMock;

	@BeforeEach
	void setUp() {
		List<Cliente> lista = List.of(CriarCliente.ClienteValido());
		BDDMockito.when(clienteServicesMock.listar()).thenReturn(lista);

		BDDMockito.when(clienteServicesMock.buscarPorNome(ArgumentMatchers.anyString())).thenReturn(lista);

		BDDMockito.when(clienteServicesMock.buscar(ArgumentMatchers.anyLong()))
				.thenReturn(CriarCliente.ClienteValido());

		BDDMockito.when(clienteServicesMock.salvar(ArgumentMatchers.any(Cliente.class)))
				.thenReturn(CriarCliente.ClienteValido());

		BDDMockito
				.when(clienteServicesMock.atualizar(ArgumentMatchers.anyLong(),
						ArgumentMatchers.any(CriarCliente.ClienteParaSerAtualizada().getClass())))
				.thenReturn(CriarCliente.ClienteParaSerAtualizada());

		BDDMockito.doNothing().when(clienteServicesMock).remover(ArgumentMatchers.anyLong());
	}

	@Test
	void listar_RetornaUmaListaClientes_ComSucesso() {
		String nome = CriarCliente.ClienteValido().getNome();
		List<ClienteResponse> listar = clienteController.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getNome()).isEqualTo(nome);
	}

	@Test
	void listar_RetornaUmaListaDeClientesComParteDoNome_ComSucesso() {
		List<ClienteResponse> lista = clienteController.listarPorNome("a");
		Assertions.assertThat(lista).isNotNull().isNotEmpty().hasSize(1);
	}

	@Test
	void listar_RetornaUmaListaVaziaDeClientes_ComParteDoNomeNaoEncontrado() {
		BDDMockito.when(clienteServicesMock.buscarPorNome(ArgumentMatchers.anyString()))
				.thenReturn(Collections.emptyList());
		List<ClienteResponse> lista = clienteController.listarPorNome("a");
		Assertions.assertThat(lista).isNotNull().isEmpty();
	}

	@Test
	void buscarPeloId_RetornaUmCliente_ComSucesso() {
		ClienteResponse cliente = clienteController.buscar(1L);
		Assertions.assertThat(cliente).isNotNull();
		Assertions.assertThat(cliente.getId().compareTo(1l));
	}

	@Test
	void salvar_RetornaUmClienteSalvo_ComSucesso() {
		ClienteResponse clienteSalvo = clienteController
				.salvar(CriarClienteRequest.salvarClienteRequest());
		Assertions.assertThat(clienteSalvo).isNotNull();
		Assertions.assertThat(clienteSalvo.getNome())
				.isEqualTo(CriarClienteRequest.salvarClienteRequest().getNome());
	}

	@Test
	void atualizar_RetornaUmClienteAtualizado_ComSucesso() {
		ClienteResponse clienteAtualizado = clienteController.atualizar(1l, CriarClienteRequest.atualizarClienteRequest());
		Assertions.assertThat(clienteAtualizado).isNotNull();
		Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(CriarClienteRequest.atualizarClienteRequest().getNome());
	}
	
	@Test
	void deletar_DeletaUmCliente_ComSucesso() {
		Assertions.assertThatCode(() -> clienteController.deletar(1l)).doesNotThrowAnyException();
		ResponseEntity<?> entity = clienteController.deletar(1l);
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
