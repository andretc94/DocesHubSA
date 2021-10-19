	package br.com.mv.doceshub.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@PositiveOrZero
	private BigDecimal valorPago;
	
	private BigDecimal valorTotal;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "venda_id")
	private List<ItensVenda> itens = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name ="cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id")
	private FormaPagamento formaPagamento;
	
	private LocalDateTime dataCompra;

	private LocalDateTime dataPagamento;
	
	private Boolean pago;
	
}