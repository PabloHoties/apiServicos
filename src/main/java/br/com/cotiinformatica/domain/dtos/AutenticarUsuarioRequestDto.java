package br.com.cotiinformatica.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AutenticarUsuarioRequestDto {

	@NotBlank(message = "Por favor, informe o email do usuário.")
	@Email(message = "Por favor, informe um endereço de email válido.")
	private String email;
	
	@NotBlank(message = "Por favor, informe a senha do usuário.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Informe a senha com letras maíusculas, minúsculas, números, caracteres especiais e pelo menos 8 caracteres.")
	private String senha;
}
