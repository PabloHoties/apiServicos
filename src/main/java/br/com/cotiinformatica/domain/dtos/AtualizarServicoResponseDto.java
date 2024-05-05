package br.com.cotiinformatica.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AtualizarServicoResponseDto {

	private UUID idServico;
	private String nome;
	private String descricao;
	private Double valor;
	private UUID idProfissional;
}
