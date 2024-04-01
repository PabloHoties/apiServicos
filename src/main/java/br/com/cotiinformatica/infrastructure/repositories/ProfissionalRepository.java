package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.domain.entities.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {

	@Query("select pr from Profissional pr where pr.email = :pEmail")
	Profissional findByEmail(@Param("pEmail") String email);
	
	@Query("select pr from Profissional pr where pr.cpf = :pCpf")
	Profissional findByCpf(@Param("pCpf") String cpf);
}
