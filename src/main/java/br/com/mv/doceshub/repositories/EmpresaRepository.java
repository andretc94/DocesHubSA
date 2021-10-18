package br.com.mv.doceshub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mv.doceshub.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	List<Empresa> findByNome(String nome);
	
	List<Empresa> findByNomeContainingIgnoreCase(String nome);

}
