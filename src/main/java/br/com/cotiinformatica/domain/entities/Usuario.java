package br.com.cotiinformatica.domain.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_usuario")
public class Usuario {

	@Id
	@Column(name = "idusuario")
	private UUID idUsuario;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@Column(name = "senha", length = 100, nullable = false)
	private String senha;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datacadastro", nullable = false)
	private Date dataCadastro;
}
