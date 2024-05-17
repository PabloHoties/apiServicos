package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarUsuarioResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarUsuarioResponseDto;
import br.com.cotiinformatica.domain.entities.Usuario;
import br.com.cotiinformatica.domain.interfaces.UsuarioDomainService;
import br.com.cotiinformatica.infrastructure.components.JwtTokenComponent;
import br.com.cotiinformatica.infrastructure.components.SHA256Component;
import br.com.cotiinformatica.infrastructure.repositories.UsuarioRepository;

@Service
public class UsuarioDomainServiceImpl implements UsuarioDomainService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	SHA256Component sha256Component;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	JwtTokenComponent jwtTokenComponent;

	@Override
	public CriarUsuarioResponseDto criarUsuario(CriarUsuarioRequestDto dto) {

		if (usuarioRepository.findByEmail(dto.getEmail()) != null)
			throw new IllegalArgumentException("O email informado já está cadastrado.");

		Usuario usuario = modelMapper.map(dto, Usuario.class);
		usuario.setIdUsuario(UUID.randomUUID());
		usuario.setSenha(sha256Component.criptografarSHA256(dto.getSenha()));
		usuario.setDataCadastro(new Date());

		usuarioRepository.save(usuario);

		CriarUsuarioResponseDto response = modelMapper.map(usuario, CriarUsuarioResponseDto.class);
		return response;
	}

	@Override
	public AutenticarUsuarioResponseDto autenticarUsuario(AutenticarUsuarioRequestDto dto) {

		Usuario usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(),
				sha256Component.criptografarSHA256(dto.getSenha()));

		if (usuario != null) {
			AutenticarUsuarioResponseDto response = modelMapper.map(usuario, AutenticarUsuarioResponseDto.class);
			response.setDataHoraAcesso(new Date());

			try {
				response.setToken(jwtTokenComponent.generateToken(usuario.getEmail()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return response;
		} else {
			throw new IllegalAccessError("Acesso negado. Email ou senha incorretos.");
		}

	}

}
