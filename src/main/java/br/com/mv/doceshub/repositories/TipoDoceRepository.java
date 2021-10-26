package br.com.mv.doceshub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mv.doceshub.model.TipoDoce;

@Repository
public interface TipoDoceRepository extends JpaRepository<TipoDoce, Long> {
	
	List<TipoDoce> findByDescricao(String descricao);

	List<TipoDoce> findByDescricaoContainingIgnoreCase(String descricao);
	
}
