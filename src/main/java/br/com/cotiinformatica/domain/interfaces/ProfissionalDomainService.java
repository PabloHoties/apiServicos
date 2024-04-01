package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.AtualizarProfissionalRequestDto;
import br.com.cotiinformatica.domain.dtos.AtualizarProfissionalResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarProfissionaisResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarProfissionalRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarProfissionalResponseDto;

public interface ProfissionalDomainService {

	CriarProfissionalResponseDto criarProfissional(CriarProfissionalRequestDto dto);
	
	List<ConsultarProfissionaisResponseDto> consultarProfissionais();
	
	ConsultarProfissionaisResponseDto obterProfissional(UUID id);
	
	AtualizarProfissionalResponseDto atualizarProfissional(AtualizarProfissionalRequestDto dto);
	
	void deletarProfissional(UUID id);
}
