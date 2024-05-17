package br.com.cotiinformatica.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarUsuarioResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarUsuarioResponseDto;
import br.com.cotiinformatica.domain.interfaces.UsuarioDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuariosController {

	@Autowired
	UsuarioDomainService usuarioDomainService;
	
	@PostMapping("criar")
	ResponseEntity<CriarUsuarioResponseDto> criar(@RequestBody @Valid CriarUsuarioRequestDto dto) {
		
		CriarUsuarioResponseDto response = usuarioDomainService.criarUsuario(dto);
		return ResponseEntity.status(201).body(response);
	}
	
	@PostMapping("autenticar")
	ResponseEntity<AutenticarUsuarioResponseDto> autenticar(@RequestBody @Valid AutenticarUsuarioRequestDto dto) {
		
		AutenticarUsuarioResponseDto response = usuarioDomainService.autenticarUsuario(dto);
		return ResponseEntity.status(200).body(response);
	}
}
