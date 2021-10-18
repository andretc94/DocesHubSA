package br.com.mv.doceshub.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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

import br.com.mv.doceshub.dto.request.empresa.EmpresaRequest;
import br.com.mv.doceshub.dto.response.empresa.EmpresaResponse;
import br.com.mv.doceshub.model.Empresa;
import br.com.mv.doceshub.services.EmpresaServices;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaServices empresaServices;

	@GetMapping
	@ApiOperation("Listar todas empresas")
	public List<EmpresaResponse> listar() {
		return EmpresaResponse.converter(empresaServices.listar());
	}
	
	@GetMapping("/por-nome/")
	@ApiOperation("Buscar empresa pelo nome")
	public List<EmpresaResponse> buscarPorNome(@RequestParam @NotBlank String nome) {
		return EmpresaResponse.converter(empresaServices.buscarPorNome(nome));
	}
	

	@GetMapping("/{id}")
	@ApiOperation("Buscar empresa pelo ID")
	public EmpresaResponse buscar(@PathVariable @PositiveOrZero Long id) {
		return EmpresaResponse.converter(empresaServices.buscar(id));
	}

	@PostMapping
	@ApiOperation("Salvar uma empresa")
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaResponse salvar(@RequestBody @Valid EmpresaRequest novaEmpresa){
		Empresa empresaNova = EmpresaRequest.converter(novaEmpresa);
		return EmpresaResponse.converter(empresaServices.salvar(empresaNova));
	}

	@PutMapping("/{id}")
	@ApiOperation("Atualizar uma empresa pelo ID")
	public EmpresaResponse atualizar(@PathVariable @PositiveOrZero Long id, @RequestBody @Valid EmpresaRequest novaEmpresa) {
		Empresa empresaNova = EmpresaRequest.converter(novaEmpresa);
		return EmpresaResponse.converter(empresaServices.atualizar(id, empresaNova));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Deletar uma empresa pelo ID")
	public ResponseEntity<?> deletar(@PathVariable(required = true) @PositiveOrZero @NotNull @NotBlank Long id){
		empresaServices.remover(id);
		return ResponseEntity.ok("Deletado com sucesso!");
	}

}
