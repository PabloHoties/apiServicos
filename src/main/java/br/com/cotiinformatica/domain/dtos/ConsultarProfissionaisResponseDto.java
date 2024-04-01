package br.com.cotiinformatica.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ConsultarProfissionaisResponseDto {

	private UUID IdProfissional;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
}
