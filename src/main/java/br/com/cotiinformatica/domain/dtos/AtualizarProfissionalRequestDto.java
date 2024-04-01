package br.com.cotiinformatica.domain.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AtualizarProfissionalRequestDto {

	@NotNull(message = "Por favor, informe o ID do profissional.")
	private UUID IdProfissional;
	
	@NotBlank(message = "Por favor, informe o nome do profissional.")
	@Size(min = 8, max = 150, message = "Por favor, informe um nome de 8 a 150 caracteres.")
	private String nome;
	
	@NotBlank(message = "Por favor, informe o email do profissional.")
	@Email(message = "Por favor, informe um endereço de email válido.")
	private String email;
	
	@NotBlank(message = "Por favor, informe o CPF do profissional.")
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Por favor, informe um CPF no formato: '000.000.000-00'.")
	private String cpf;
	
	@NotBlank(message = "Por favor, informe o telefone do profissional.")
	@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}", message = "Por favor, informe um telefone no formato: '(00) 00000-0000'.")
	private String telefone;
}
