package br.com.mv.doceshub.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class FormaPagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "a descricao nao pode ser vazia ou nula")
	@Column(unique = true)
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "formaPagamento")
	private List<Venda> vendas;
}