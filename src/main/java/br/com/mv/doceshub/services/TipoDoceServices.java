package br.com.mv.doceshub.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mv.doceshub.exceptions.DescricaoNomeJaExisteException;
import br.com.mv.doceshub.exceptions.EntidadeEmUsoException;
import br.com.mv.doceshub.exceptions.QuantidadeEstoqueException;
import br.com.mv.doceshub.exceptions.TipoDoceNaoEncontradoException;
import br.com.mv.doceshub.model.TipoDoce;
import br.com.mv.doceshub.repositories.TipoDoceRepository;

@Service
public class TipoDoceServices {

	@Autowired
	private TipoDoceRepository tipoDoceRepository;

	public TipoDoce salvar(TipoDoce novoTipoDoce) {
		if(novoTipoDoce.getQtdEstoque()<0) {
			throw new QuantidadeEstoqueException("A quantidade do estoque não pode ser inferior a 0");
		}
		try {
			return tipoDoceRepository.save(novoTipoDoce);						
		} catch (DataIntegrityViolationException e) {
			throw new DescricaoNomeJaExisteException();
		}
	}

	public List<TipoDoce> listar(){
		return tipoDoceRepository.findAll();
	}

	public List<TipoDoce> buscarPorDescricao(String descricao) {
		return tipoDoceRepository.findByDescricaoContainingIgnoreCase(descricao);
	}

	public TipoDoce buscar(Long id) {
		return tipoDoceRepository.findById(id)
				.orElseThrow(()-> new TipoDoceNaoEncontradoException(id));
	}

	public TipoDoce atualizar(Long id, TipoDoce novoTipoDoce) {
		TipoDoce tipoDoceantigo = this.buscar(id);
		BeanUtils.copyProperties(novoTipoDoce, tipoDoceantigo, "id");
		return this.salvar(tipoDoceantigo);
	}
	
	public TipoDoce atualizarEstoque(Long id, Integer quantidade) {
		TipoDoce tipoDoce = this.buscar(id);

		int novoEstoque = tipoDoce.getQtdEstoque() - quantidade;
		
		if(novoEstoque < 0) {
			throw new QuantidadeEstoqueException("A quantidade solicitada não possui estoque disponivel");
		}
		
		tipoDoce.setQtdEstoque(novoEstoque);
		return tipoDoceRepository.save(tipoDoce);
	}
	

	public void remover(Long id) {
		try {
			tipoDoceRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new TipoDoceNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException();
		}
	}

}
