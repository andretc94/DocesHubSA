package br.com.mv.doceshub.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.doceshub.dto.request.cliente.ClienteRequest;
import br.com.mv.doceshub.dto.response.cliente.ClienteInadimplenteResponse;
import br.com.mv.doceshub.dto.response.cliente.ClienteResponse;
import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.services.ClienteServices;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteServices clienteServices;

	@ApiOperation("Listar todos os clientes")
	@GetMapping
	public List<ClienteResponse> listar() {
		return ClienteResponse.converter(clienteServices.listar());
	}
	
	@ApiOperation("Listar todos os clientes inadimplentes")
	@GetMapping("/inadimplentes")
	public List<ClienteInadimplenteResponse> listarInadimplentes() {
		List<Cliente> listarInadimplente = clienteServices.listarInadimplente();
		return ClienteInadimplenteResponse.converter(listarInadimplente);
	}
	
	@ApiOperation("Buscar cliente pelo nome")
	@GetMapping("/por-nome/")
	public List<ClienteResponse> listarPorNome(@RequestParam @NotBlank String nome){
		return ClienteResponse.converter(clienteServices.buscarPorNome(nome));
	}

	@ApiOperation("Buscar clientes pelo ID")
	@GetMapping("/{id}")
	public ClienteResponse buscar(@PathVariable @NotNull Long id) {
		return ClienteResponse.converter(clienteServices.buscar(id));
	}

	@ApiOperation("Salvar um cliente")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponse salvar(@RequestBody @Valid ClienteRequest novoCliente){
		Cliente salvo = clienteServices.salvar(ClienteRequest.converter(novoCliente));
		return ClienteResponse.converter(salvo);
	}

	@ApiOperation("Atualizar um cliente")
	@PutMapping("/{id}")
	public ClienteResponse atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid ClienteRequest novoCliente) {
		Cliente clienteNovo = ClienteRequest.converter(novoCliente);
		Cliente ClienteAtualizado = clienteServices.atualizar(id, clienteNovo);
		return ClienteResponse.converter(ClienteAtualizado);			
	}

	@ApiOperation("Deletar um cliente")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable @NotNull Long id){
		clienteServices.remover(id);
		return ResponseEntity.ok("Deletado com sucesso!");
	}

}
