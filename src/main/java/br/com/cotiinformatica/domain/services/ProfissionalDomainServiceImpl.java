package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.AtualizarProfissionalRequestDto;
import br.com.cotiinformatica.domain.dtos.AtualizarProfissionalResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarProfissionaisResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarProfissionalRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarProfissionalResponseDto;
import br.com.cotiinformatica.domain.entities.Profissional;
import br.com.cotiinformatica.domain.interfaces.ProfissionalDomainService;
import br.com.cotiinformatica.infrastructure.repositories.ProfissionalRepository;

@Service
public class ProfissionalDomainServiceImpl implements ProfissionalDomainService {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CriarProfissionalResponseDto criarProfissional(CriarProfissionalRequestDto dto) {

		if (profissionalRepository.findByEmail(dto.getEmail()) != null)
			throw new IllegalArgumentException("O email informado já está cadastrado.");

		if (profissionalRepository.findByCpf(dto.getCpf()) != null)
			throw new IllegalArgumentException("O CPF informado já está cadastrado.");

		Profissional profissional = modelMapper.map(dto, Profissional.class);

		profissional.setIdProfissional(UUID.randomUUID());

		profissionalRepository.save(profissional);

		CriarProfissionalResponseDto response = modelMapper.map(profissional, CriarProfissionalResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}

	@Override
	public List<ConsultarProfissionaisResponseDto> consultarProfissionais() {

		List<Profissional> profissionais = profissionalRepository.findAll();

		List<ConsultarProfissionaisResponseDto> response = modelMapper.map(profissionais,
				new TypeToken<List<ConsultarProfissionaisResponseDto>>() {
				}.getType());

		return response;
	}

	@Override
	public ConsultarProfissionaisResponseDto obterProfissional(UUID id) {

		Optional<Profissional> profissional = profissionalRepository.findById(id);

		if (profissional.isPresent()) {
			ConsultarProfissionaisResponseDto response = modelMapper.map(profissional,
					ConsultarProfissionaisResponseDto.class);
			return response;
		} else {
			return null;
		}
	}

	@Override
	public AtualizarProfissionalResponseDto atualizarProfissional(AtualizarProfissionalRequestDto dto) {

		if (profissionalRepository.findById(dto.getIdProfissional()).isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um profissional cadastrado.");

		Profissional profissional = modelMapper.map(dto, Profissional.class);

		profissionalRepository.save(profissional);

		AtualizarProfissionalResponseDto response = modelMapper.map(profissional,
				AtualizarProfissionalResponseDto.class);

		return response;
	}

	@Override
	public void deletarProfissional(UUID id) {

		Optional<Profissional> profissionalOpt = profissionalRepository.findById(id);

		if (profissionalOpt.isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um profissional cadastrado.");

		Profissional profissional = profissionalOpt.get();

		profissionalRepository.delete(profissional);

	}

}
