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

import br.com.mv.doceshub.dto.request.formapagamento.FormaPagamentoRequest;
import br.com.mv.doceshub.dto.response.formapagamento.FormaPagamentoResponse;
import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.services.FormaPagamentoServices;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/formaspagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoServices pagamentoService;

	@GetMapping
	@ApiOperation("Listar todas Formas de Pagamento")
	public List<FormaPagamentoResponse> listar() {
		return FormaPagamentoResponse.converter(pagamentoService.listar());
	}

	@GetMapping("/por-descricao/")
	@ApiOperation("Buscar todas Formas de Pagamento pela descrição")
	public List<FormaPagamentoResponse> buscarPorDescricao(@RequestParam @NotEmpty String descricao) {
		return FormaPagamentoResponse.converter(pagamentoService.buscarPorDescricao(descricao));
	}

	@GetMapping("/{id}")
	@ApiOperation("Buscar uma Forma de Pagamento pelo ID")
	public FormaPagamentoResponse buscar(@PathVariable @Valid @NotNull Long id) {
		return FormaPagamentoResponse.converter(pagamentoService.buscar(id));
	}

	@PostMapping
	@ApiOperation("Salvar uma Forma de Pagamento")
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoResponse salvar(@RequestBody @Valid FormaPagamentoRequest formaPagamento) {
		FormaPagamento formaPagamentoSalva = pagamentoService.salvar(FormaPagamentoRequest.converter(formaPagamento));
		return FormaPagamentoResponse.converter(formaPagamentoSalva);
	}

	@ApiOperation("Atualizar uma Forma de Pagamento pelo ID")
	@PutMapping("/{id}")
	public FormaPagamentoResponse atualizar(@PathVariable @NotNull Long id,
			@RequestBody @Valid FormaPagamentoRequest formaPagamento) {
		FormaPagamento convertida = FormaPagamentoRequest.converter(formaPagamento);
		FormaPagamento atualizada = pagamentoService.atualizar(id, convertida);
		return FormaPagamentoResponse.converter(atualizada);
	}

	@ApiOperation("Deletar uma Forma de Pagamento")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable @NotNull Long id) {
		pagamentoService.remover(id);
		return ResponseEntity.ok("Forma de Pagamento deletada com sucesso!");
	}

}
