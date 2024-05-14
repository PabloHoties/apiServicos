package br.com.cotiinformatica.domain.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.AtualizarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.AtualizarServicoResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarProfissionaisResponseDto;
import br.com.cotiinformatica.domain.dtos.ConsultarServicosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarServicoResponseDto;
import br.com.cotiinformatica.domain.entities.Profissional;
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

		Optional<Profissional> profissional = profissionalRepository.findById(dto.getIdProfissional());

		if (profissional.isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um profissional cadastrado.");

		Servico servico = modelMapper.map(dto, Servico.class);

		servico.setIdServico(UUID.randomUUID());
		servico.setProfissional(profissional.get());

		servicoRepository.save(servico);

		CriarServicoResponseDto response = modelMapper.map(servico, CriarServicoResponseDto.class);
		response.setIdProfissional(servico.getProfissional().getIdProfissional());
		response.setDataHoraCadastro(new Date());

		return response;
	}

	@Override
	public AtualizarServicoResponseDto atualizarServico(AtualizarServicoRequestDto dto) {

		if (servicoRepository.findById(dto.getIdServico()).isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um serviço cadastrado.");
		
		Optional<Profissional> profissionalOpt = profissionalRepository.findById(dto.getIdProfissional());

		if(profissionalOpt.isEmpty())
			throw new IllegalArgumentException("O ID informado não pertence a um profissional cadastrado.");

		Servico servico = modelMapper.map(dto, Servico.class);

		Profissional profissional = profissionalOpt.get();
		servico.setProfissional(profissional);

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

		Optional<Servico> servicoOptional = servicoRepository.findServicoWithProfissionalByServicoId(id);

	    if (servicoOptional.isPresent()) {
	        Servico servico = servicoOptional.get();

	        ConsultarServicosResponseDto servicoResponse = new ConsultarServicosResponseDto();
	        servicoResponse.setIdServico(servico.getIdServico());
	        servicoResponse.setNome(servico.getNome());
	        servicoResponse.setDescricao(servico.getDescricao());
	        servicoResponse.setValor(servico.getValor());

	        ConsultarProfissionaisResponseDto profissionalResponse = new ConsultarProfissionaisResponseDto();
	        profissionalResponse.setIdProfissional(servico.getProfissional().getIdProfissional());
	        profissionalResponse.setNome(servico.getProfissional().getNome());
	        profissionalResponse.setEmail(servico.getProfissional().getEmail());
	        profissionalResponse.setCpf(servico.getProfissional().getCpf());
	        profissionalResponse.setTelefone(servico.getProfissional().getTelefone());

	        // Criando uma lista com o profissional e atribuindo-a ao serviço
	        List<ConsultarProfissionaisResponseDto> listaProfissionais = new ArrayList<>();
	        listaProfissionais.add(profissionalResponse);
	        servicoResponse.setProfissionais(listaProfissionais);

	        return servicoResponse;
	    } else {
	    	throw new IllegalArgumentException("O ID informado não pertence a um serviço cadastrado.");
	    }
	}

	@Override
	public List<ConsultarServicosResponseDto> consultarServicos() {

		List<Servico> servicos = servicoRepository.findAllServicosWithProfissionais();

		List<ConsultarServicosResponseDto> response = new ArrayList<>();

		for (Servico servico : servicos) {
			ConsultarServicosResponseDto consultarServicosResponseDto = new ConsultarServicosResponseDto();
			consultarServicosResponseDto.setIdServico(servico.getIdServico());
			consultarServicosResponseDto.setNome(servico.getNome());
			consultarServicosResponseDto.setDescricao(servico.getDescricao());
			consultarServicosResponseDto.setValor(servico.getValor());

			List<ConsultarProfissionaisResponseDto> profissionaisResponseDtos = new ArrayList<>();

			ConsultarProfissionaisResponseDto profissionalDto = new ConsultarProfissionaisResponseDto();
			profissionalDto.setIdProfissional(servico.getProfissional().getIdProfissional());
			profissionalDto.setNome(servico.getProfissional().getNome());
			profissionalDto.setEmail(servico.getProfissional().getEmail());
			profissionalDto.setCpf(servico.getProfissional().getCpf());
			profissionalDto.setTelefone(servico.getProfissional().getTelefone());

			profissionaisResponseDtos.add(profissionalDto);
			consultarServicosResponseDto.setProfissionais(profissionaisResponseDtos);

			response.add(consultarServicosResponseDto);
		}

		return response;
	}

}
