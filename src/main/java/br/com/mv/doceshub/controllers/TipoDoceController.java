package br.com.mv.doceshub.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

import br.com.mv.doceshub.dto.request.tipodoce.TipoDoceRequest;
import br.com.mv.doceshub.dto.response.tipodoce.TipoDoceResponse;
import br.com.mv.doceshub.model.TipoDoce;
import br.com.mv.doceshub.services.TipoDoceServices;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/tiposdoces")
public class TipoDoceController {

	@Autowired
	private TipoDoceServices tipoDoceService;
	
	@ApiOperation("Listar todas Tipos de Doces")
	@GetMapping
	public List<TipoDoceResponse> listar() {
		List<TipoDoce> listar = tipoDoceService.listar();
		return TipoDoceResponse.converter(listar);
	}

	@ApiOperation("Buscar todas Tipos de Doces pela descricao")
	@GetMapping("/por-descricao/")
	public List<TipoDoceResponse> listar(@RequestParam @NotNull @NotEmpty String descricao) {
		List<TipoDoce> buscarPorDescricao = tipoDoceService.buscarPorDescricao(descricao);
		return TipoDoceResponse.converter(buscarPorDescricao);
	}

	@ApiOperation("Buscar um Tipo de Doce pelo ID")
	@GetMapping("/{id}")
	public TipoDoceResponse buscar(@PathVariable @NotNull Long id) {
		TipoDoce buscar = tipoDoceService.buscar(id);
		return TipoDoceResponse.converter(buscar);			
	}

	@ApiOperation("Salvar um Tipo de Doce")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoDoceResponse salvar(@RequestBody @Valid TipoDoceRequest requestDoce){
		TipoDoce novoTipoDoce = TipoDoceRequest.converter(requestDoce);
		TipoDoce salvo = tipoDoceService.salvar(novoTipoDoce);
		return TipoDoceResponse.converter(salvo);
	}

	@ApiOperation("Atualizar um Tipo de Doce pelo ID")
	@PutMapping("/{id}")
	public TipoDoceResponse atualizar(@RequestBody @Valid TipoDoceRequest requestDoce, @PathVariable Long id){
		TipoDoce novoTipoDoce = TipoDoceRequest.converter(requestDoce);
		TipoDoce atualizado = tipoDoceService.atualizar(id, novoTipoDoce);
		return TipoDoceResponse.converter(atualizado);
	}

	@ApiOperation("Deletar um Tipo de Doce")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		tipoDoceService.remover(id);
		return ResponseEntity.ok("Tipo de doce deletado com Sucesso!");
	}
}
