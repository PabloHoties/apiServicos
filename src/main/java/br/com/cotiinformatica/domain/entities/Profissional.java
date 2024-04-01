package br.com.cotiinformatica.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "profissional")
public class Profissional {

	@Id
	@Column(name = "idprofissional", nullable = false)
	private UUID IdProfissional;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "cpf", length = 14, nullable = false)
	private String cpf;

	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	@OneToMany(mappedBy = "profissional")
	private List<Servico> servicos;
}
