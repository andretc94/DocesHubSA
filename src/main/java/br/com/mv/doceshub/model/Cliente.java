package br.com.mv.doceshub.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	private String email;
	
	private String telefone;
	
	@ManyToOne(optional = false)
	private Empresa empresa;
	
	@OneToMany(mappedBy = "cliente")
	@ToString.Exclude
	private List<Venda> vendas = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Cliente cliente = (Cliente) o;
		return id != null && Objects.equals(id, cliente.id);
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
