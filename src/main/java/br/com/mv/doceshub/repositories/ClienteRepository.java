package br.com.mv.doceshub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mv.doceshub.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
	@Query("select c from Cliente c join c.vendas where pago = 'false'")
	List<Cliente> findByInadimplentes();
}
