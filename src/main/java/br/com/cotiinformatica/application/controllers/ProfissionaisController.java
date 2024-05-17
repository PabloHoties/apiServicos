package br.com.cotiinformatica.application.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.AtualizarProfissionalRequestDto;
import br.com.cotiinformatica.domain.dtos.AtualizarProfissionalResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarProfissionaisResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarProfissionalRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarProfissionalResponseDto;
import br.com.cotiinformatica.domain.interfaces.ProfissionalDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/profissionais")
public class ProfissionaisController {

	@Autowired
	private ProfissionalDomainService profissionalDomainService;

	@PostMapping("criar")
	public ResponseEntity<CriarProfissionalResponseDto> criar(@RequestBody @Valid CriarProfissionalRequestDto dto) {

		CriarProfissionalResponseDto response = profissionalDomainService.criarProfissional(dto);

		return ResponseEntity.status(201).body(response);
	}

	@PutMapping("atualizar")
	public ResponseEntity<AtualizarProfissionalResponseDto> atualizar(
			@RequestBody @Valid AtualizarProfissionalRequestDto dto) {

		AtualizarProfissionalResponseDto response = profissionalDomainService.atualizarProfissional(dto);

		return ResponseEntity.status(200).body(response);
	}

	@GetMapping("consultar")
	public ResponseEntity<List<ConsultarProfissionaisResponseDto>> consultar() {

		List<ConsultarProfissionaisResponseDto> response = profissionalDomainService.consultarProfissionais();

		if (response.isEmpty())
			return ResponseEntity.status(204).body(null);

		return ResponseEntity.status(200).body(response);

	}

	@GetMapping("obter/{id}")
	public ResponseEntity<ConsultarProfissionaisResponseDto> obter(@PathVariable("id") UUID id) {

		return ResponseEntity.of(Optional.ofNullable(profissionalDomainService.obterProfissional(id)));
	}

	@DeleteMapping("deletar/{id}")
	public ResponseEntity<String> deletar(UUID id) {

		profissionalDomainService.deletarProfissional(id);

		return ResponseEntity.status(200).body(null);
	}
}
