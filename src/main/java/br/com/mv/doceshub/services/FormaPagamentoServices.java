package br.com.mv.doceshub.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mv.doceshub.exceptions.DescricaoNomeJaExisteException;
import br.com.mv.doceshub.exceptions.EntidadeEmUsoException;
import br.com.mv.doceshub.exceptions.FormaPagamentoNaoEncontradaException;
import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.repositories.FormaPagamantoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormaPagamentoServices {

	private final FormaPagamantoRepository pagamentoRepository;

	public List<FormaPagamento> listar(){
		return pagamentoRepository.findAll();
	}

	public FormaPagamento buscar(Long id) {
		return pagamentoRepository.findById(id).orElseThrow(()-> 
		new FormaPagamentoNaoEncontradaException(id));
	}

	public List<FormaPagamento> buscarPorDescricao(String descricao) {
		return pagamentoRepository.findByDescricaoContainingIgnoreCase(descricao);
	}

	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		try {
			return pagamentoRepository.save(formaPagamento);			
		} catch (DataIntegrityViolationException e) {
			throw new DescricaoNomeJaExisteException();
		}
	}

	public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento) {
		FormaPagamento formaAtual = this.buscar(id);
		BeanUtils.copyProperties(formaPagamento, formaAtual, "id");
		return this.salvar(formaAtual);
	}

	public void remover(Long id) {
		try {
			pagamentoRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException();
		}
	}

}
