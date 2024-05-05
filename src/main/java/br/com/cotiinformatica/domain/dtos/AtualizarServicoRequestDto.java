package br.com.cotiinformatica.domain.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AtualizarServicoRequestDto {

	@NotNull(message = "Por favor, informe o ID do serviço.")
	private UUID idServico;
	
	@Size(min = 5, max = 150, message = "Por favor, informe um nome de 5 a 150 caracteres.")
	@NotBlank(message = "Por favor, informe o nome do serviço.")
	private String nome;
	
	@Size(min = 15, max = 500, message = "Por favor, informe uma descrição de 15 a 500 caracteres.")
	@NotBlank(message = "Por favor, informe a descrição do serviço.")
	private String descricao;
	
	@NotNull(message = "Por favor, informe o valor do serviço.")
	private Double valor;
	
	@NotNull(message = "Por favor, informe o ID do profissional.")
	private UUID idProfissional;
}
