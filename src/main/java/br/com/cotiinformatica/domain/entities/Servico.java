package br.com.cotiinformatica.domain.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "servico")
public class Servico {

	@Id
	@Column(name = "idservico", nullable = false)
	private UUID IdServico;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "descricao", length = 500, nullable = false)
	private String descricao;
	
	@Column(name = "valor", length = 10, nullable = false)
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "idprofissional", nullable = false)
	private Profissional profissional;
}
