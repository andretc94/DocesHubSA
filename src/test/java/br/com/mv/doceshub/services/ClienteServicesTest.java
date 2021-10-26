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

import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.repositories.ClienteRepository;
import br.com.mv.doceshub.utils.CriarCliente;
import br.com.mv.doceshub.utils.CriarEmpresa;

@ExtendWith(SpringExtension.class)
class ClienteServicesTest {
	
	@InjectMocks
	ClienteServices clienteService;

	@Mock
	ClienteRepository clienteRepositoryMock;
	
	@Mock
	EmpresaServices empresaServicesMock;

	@BeforeEach
	void setUp() throws Exception {
		List<Cliente> lista = List.of(CriarCliente.ClienteValido());
		Optional<Cliente> optional = Optional.of(CriarCliente.ClienteValido());
		
		BDDMockito.when(clienteRepositoryMock.findAll()).thenReturn(lista);
		
		BDDMockito.when(clienteRepositoryMock.findByInadimplentes()).thenReturn(lista);
		
		BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(optional);
		
		BDDMockito.when(clienteRepositoryMock.findByNomeContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(lista);
		
		BDDMockito.when(clienteRepositoryMock.save(ArgumentMatchers.any(Cliente.class))).thenReturn(CriarCliente.ClienteValido());
		
		BDDMockito.when(empresaServicesMock.buscar(ArgumentMatchers.anyLong())).thenReturn(CriarEmpresa.empresaValida());
	}

	@Test
	void testListar() {
		String nome = CriarCliente.ClienteValido().getNome();
		List<Cliente> listar = clienteService.listar();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
		Assertions.assertThat(listar.get(0).getNome()).isEqualTo(nome);
	}

	@Test
	void testListarInadimplente() {
		List<Cliente> listar = clienteService.listarInadimplente();
		Assertions.assertThat(listar).isNotNull();
		Assertions.assertThat(listar).isNotEmpty().hasSize(1);
	}

	@Test
	void testBuscar() {
		Cliente buscar = clienteService.buscar(1l);
		Assertions.assertThat(buscar).isNotNull().isEqualTo(CriarCliente.ClienteValido());
	}

	@Test
	void testBuscarPorNome() {
		List<Cliente> buscarPorNome = clienteService.buscarPorNome("a");
		Assertions.assertThat(buscarPorNome).isNotNull();
		Assertions.assertThat(buscarPorNome).isNotEmpty().hasSize(1);
	}

	@Test
	void testSalvar() {
		Cliente salvar = clienteService.salvar(CriarCliente.ClienteParaSerSalva());
		Assertions.assertThat(salvar).isNotNull();
		Assertions.assertThat(salvar.getId()).isNotNull();
	}

	@Test
	void testAtualizar() {
		Cliente atualizar = clienteService.atualizar(1l, CriarCliente.ClienteParaSerAtualizada());
		Assertions.assertThat(atualizar).isNotNull();
		Assertions.assertThat(atualizar.getNome()).isEqualTo(CriarCliente.ClienteParaSerAtualizada().getNome());
	}

	@Test
	void testRemover() {
		Assertions.assertThatCode(()->clienteService.remover(1l)).doesNotThrowAnyException();
	}

}
