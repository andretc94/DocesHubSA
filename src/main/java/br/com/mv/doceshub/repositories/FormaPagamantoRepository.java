package br.com.mv.doceshub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mv.doceshub.model.FormaPagamento;

@Repository
public interface FormaPagamantoRepository extends JpaRepository<FormaPagamento, Long>{
	
	List<FormaPagamento> findByDescricao(String descricao);
	
	List<FormaPagamento> findByDescricaoContainingIgnoreCase(String descricao);
	
}
