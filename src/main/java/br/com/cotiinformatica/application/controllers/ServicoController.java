package br.com.cotiinformatica.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.AtualizarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AtualizarServicoResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarServicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoResponseDto;
import br.com.cotiinformatica.domain.interfaces.ServicoDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/servicos")
public class ServicoController {

	@Autowired
	private ServicoDomainService servicoDomainService;

	@PostMapping("criar")
	public ResponseEntity<CriarServicoResponseDto> criar(@RequestBody @Valid CriarServicoRequestDto dto) {

		CriarServicoResponseDto response = servicoDomainService.criarServico(dto);
		return ResponseEntity.status(201).body(response);
	}

	@PutMapping("atualizar")
	public ResponseEntity<AtualizarServicoResponseDto> atualizar(@RequestBody @Valid AtualizarServicoRequestDto dto) {

		AtualizarServicoResponseDto response = servicoDomainService.atualizarServico(dto);
		return ResponseEntity.status(200).body(response);
	}

	@DeleteMapping("deletar/{id}")
	public ResponseEntity<String> deletar(UUID id) {

		servicoDomainService.deletarServico(id);
		return ResponseEntity.status(200).body(null);
	}

	@GetMapping("obter/{id}")
	public ResponseEntity<ConsultarServicosResponseDto> obter(UUID id) {

		ConsultarServicosResponseDto response = servicoDomainService.obterServico(id);
		return ResponseEntity.status(200).body(response);
	}

	@GetMapping("consultar")
	public ResponseEntity<List<ConsultarServicosResponseDto>> consultar() {

		List<ConsultarServicosResponseDto> response = servicoDomainService.consultarServicos();

		if (response.isEmpty())
			return ResponseEntity.status(204).body(null);

		return ResponseEntity.status(200).body(response);
	}
}