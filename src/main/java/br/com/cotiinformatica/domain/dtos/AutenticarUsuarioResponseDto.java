package br.com.cotiinformatica.domain.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class AutenticarUsuarioResponseDto {

	private String idUsuario;
	private String nome;
	private String email;
	private String token;
	private Date dataHoraAcesso;
}
