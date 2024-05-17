package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	@Query("select us from Usuario us where us.email = :pEmail")
	Usuario findByEmail(@Param("pEmail") String email);
	
	@Query("select us from Usuario us where us.email = :pEmail and us.senha = :pSenha")
	Usuario findByEmailAndSenha(@Param("pEmail") String email, @Param("pSenha") String senha);
}
