package br.com.mv.doceshub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ItensVenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "tipoDeDoce_id", nullable = false)
	private TipoDoce tipoDeDoce = new TipoDoce();
	
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "venda_id")
//	private Venda venda;
}
