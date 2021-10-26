package br.com.mv.doceshub.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mv.doceshub.exceptions.ClienteNaoEncontradoException;
import br.com.mv.doceshub.exceptions.DescricaoNomeJaExisteException;
import br.com.mv.doceshub.exceptions.EntidadeEmUsoException;
import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.model.Empresa;
import br.com.mv.doceshub.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServices {
	
	private final ClienteRepository clienteRepository;
	private final EmpresaServices empresaServices;

	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	public List<Cliente> listarInadimplente() {
		return clienteRepository.findByInadimplentes();
	}

	public Cliente buscar(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
	}

	public List<Cliente> buscarPorNome(String nome) {
		return clienteRepository.findByNomeContainingIgnoreCase(nome);
	}

	public Cliente salvar(Cliente novoCliente) {
		Empresa empresa = empresaServices.buscar(novoCliente.getEmpresa().getId());
		novoCliente.setEmpresa(empresa);

		try {
			return clienteRepository.save(novoCliente);
		} catch (DataIntegrityViolationException e) {
			throw new DescricaoNomeJaExisteException();
		}
	}

	public Cliente atualizar(Long id, Cliente novoCliente) {
		Empresa empresa = empresaServices.buscar(novoCliente.getEmpresa().getId());
		Cliente clienteAtual = this.buscar(id);

		BeanUtils.copyProperties(novoCliente, clienteAtual, "id");

		clienteAtual.setEmpresa(empresa);

		return this.salvar(clienteAtual);
	}

	public void remover(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException();
		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException(id);
		}
	}

}
