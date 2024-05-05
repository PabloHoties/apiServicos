package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.AtualizarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AtualizarServicoResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarServicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoResponseDto;

public interface ServicoDomainService {

	CriarServicoResponseDto criarServico(CriarServicoRequestDto dto);
	
	AtualizarServicoResponseDto atualizarServico(AtualizarServicoRequestDto dto);
	
	void deletarServico(UUID id);
	
	ConsultarServicosResponseDto obterServico(UUID id);
	
	List<ConsultarServicosResponseDto> consultarServicos();
}
