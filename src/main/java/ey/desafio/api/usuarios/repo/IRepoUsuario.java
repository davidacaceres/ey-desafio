package ey.desafio.api.usuarios.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ey.desafio.api.usuarios.dom.Usuario;

public interface IRepoUsuario extends JpaRepository<Usuario, UUID> {

	boolean existsByEmail(String email);

	int deleteByEmail(String email);

	boolean existsByEmailAndIdUserNot(String email, UUID idUser);
}
