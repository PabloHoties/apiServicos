package br.com.cotiinformatica.domain.interfaces;

import br.com.cotiinformatica.domain.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarUsuarioResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarUsuarioResponseDto;

public interface UsuarioDomainService {

	CriarUsuarioResponseDto criarUsuario(CriarUsuarioRequestDto dto);
	
	AutenticarUsuarioResponseDto autenticarUsuario(AutenticarUsuarioRequestDto dto);
}
