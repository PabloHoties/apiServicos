package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.AtualizarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AtualizarServicoResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarServicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoResponseDto;
import br.com.cotiinformatica.domain.entities.Servico;
import br.com.cotiinformatica.domain.interfaces.ServicoDomainService;
import br.com.cotiinformatica.infrastructure.repositories.ProfissionalRepository;
import br.com.cotiinformatica.infrastructure.repositories.ServicoRepository;

@Service
public class ServicoDomainServiceImpl implements ServicoDomainService {

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CriarServicoResponseDto criarServico(CriarServicoRequestDto dto) {

		if (profissionalRepository.findById(dto.getIdProfissional()).isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um profissional cadastrado.");

		Servico servico = modelMapper.map(dto, Servico.class);

		servico.setIdServico(UUID.randomUUID());

		servicoRepository.save(servico);

		CriarServicoResponseDto response = modelMapper.map(servico, CriarServicoResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}

	@Override
	public AtualizarServicoResponseDto atualizarServico(AtualizarServicoRequestDto dto) {

		if (profissionalRepository.findById(dto.getIdServico()).isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um profissional cadastrado.");

		Servico servico = modelMapper.map(dto, Servico.class);

		servicoRepository.save(servico);

		AtualizarServicoResponseDto response = modelMapper.map(servico, AtualizarServicoResponseDto.class);

		return response;
	}

	@Override
	public void deletarServico(UUID id) {

		Optional<Servico> servicoOpt = servicoRepository.findById(id);

		if (servicoOpt.isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um serviço cadastrado.");

		Servico servico = servicoOpt.get();

		servicoRepository.delete(servico);
	}

	@Override
	public ConsultarServicosResponseDto obterServico(UUID id) {

		Optional<Servico> servico = servicoRepository.findById(id);

		if (servico.isPresent()) {
			ConsultarServicosResponseDto response = modelMapper.map(servico, ConsultarServicosResponseDto.class);
			return response;
		} else {
			return null;
		}
	}

	@Override
	public List<ConsultarServicosResponseDto> consultarServicos() {

		List<Servico> servicos = servicoRepository.findAll();

		List<ConsultarServicosResponseDto> response = modelMapper.map(servicos,
				new TypeToken<List<ConsultarServicosResponseDto>>() {
				}.getType());
		
		return response;
	}

}
