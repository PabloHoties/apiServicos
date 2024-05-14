package br.com.cotiinformatica.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.domain.entities.Servico;

public interface ServicoRepository extends JpaRepository<Servico, UUID> {

	@Query("select se from Servico se join fetch se.profissional")
	List<Servico> findAllServicosWithProfissionais();
	
	@Query("select se from Servico se join fetch se.profissional where se.IdServico = :pIdServico")
	Optional<Servico> findServicoWithProfissionalByServicoId(@Param("pIdServico") UUID id);
	
	@Query("select se from Servico se join fetch se.profissional where se.profissional.IdProfissional = :pIdProfissional")
	Optional<Servico> findServicoWithProfissionalByProfissionalId(@Param("pIdProfissional") UUID id);
}
