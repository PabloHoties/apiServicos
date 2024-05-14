package br.com.cotiinformatica.domain.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ConsultarServicosResponseDto {

	private UUID idServico;
	private String nome;
	private String descricao;
	private Double valor;
	private List<ConsultarProfissionaisResponseDto> profissionais;
}
