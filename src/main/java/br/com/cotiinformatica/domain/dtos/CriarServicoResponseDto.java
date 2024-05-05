package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarServicoResponseDto {

	private UUID idServico;
	private String nome;
	private String descricao;
	private Double valor;
	private UUID IdProfissional;
	private Date dataHoraCadastro;
	
}
