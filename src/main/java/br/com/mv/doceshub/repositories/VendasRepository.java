package br.com.mv.doceshub.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mv.doceshub.model.Cliente;
import br.com.mv.doceshub.model.FormaPagamento;
import br.com.mv.doceshub.model.TipoDoce;
import br.com.mv.doceshub.model.Venda;

@Repository
public interface VendasRepository extends JpaRepository<Venda, Long>{
	
	@Query("select v from Venda v join fetch v.itens i where i.tipoDeDoce = :tipoDoce")
	List<Venda> findByTipoDeDoce(@Param(value = "tipoDoce") TipoDoce tipoDoce);

	List<Venda> findByCliente(Cliente cliente);
	
	@Query("select v from Venda v where v.dataCompra = :data") 
	List<Venda> teste(@Param(value = "data") LocalDateTime data);
	
	List<Venda> findByDataCompraBetween(LocalDateTime inicio, LocalDateTime fim);
	
	List<Venda> findByFormaPagamento(FormaPagamento formaPagamento);

	List<Venda> findByDataPagamentoBetween(LocalDateTime inicio, LocalDateTime fim);
}