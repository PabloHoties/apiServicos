package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarProfissionalResponseDto {

	private UUID IdProfissional;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Date dataHoraCadastro;
	
}
