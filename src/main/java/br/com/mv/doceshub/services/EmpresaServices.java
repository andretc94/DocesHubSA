package br.com.mv.doceshub.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mv.doceshub.exceptions.DescricaoNomeJaExisteException;
import br.com.mv.doceshub.exceptions.EmpresaNaoEncontradaException;
import br.com.mv.doceshub.exceptions.EntidadeEmUsoException;
import br.com.mv.doceshub.model.Empresa;
import br.com.mv.doceshub.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaServices {

	private final EmpresaRepository empresaRepository;
	
	public List<Empresa> listar(){
		return empresaRepository.findAll();
	}
	
	public Empresa buscar(Long id) {
		return empresaRepository.findById(id)
				.orElseThrow(()-> new EmpresaNaoEncontradaException(id));
	}
	
	public List<Empresa> buscarPorNome(String nome) {
		return empresaRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public Empresa salvar(Empresa novaEmpresa) {
		try {
			return empresaRepository.save(novaEmpresa);			
		} catch (DataIntegrityViolationException e) {
			throw new DescricaoNomeJaExisteException();
		}
	}
	
	public Empresa atualizar(Long id, Empresa novaEmpresa) {
		Empresa empresaAtual = this.buscar(id);
		BeanUtils.copyProperties(novaEmpresa, empresaAtual , "id");
		return this.salvar(empresaAtual);
	}
	
	public void remover(Long id) {
		try {
			empresaRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new EmpresaNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Empresa");
		}
	}
	
}
