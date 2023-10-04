package ey.desafio.api.usuarios.repo;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import ey.desafio.api.usuarios.dom.Telefono;

public interface IRepoTelefono extends JpaRepository<Telefono, UUID> {

	@Transactional
    void deleteByUsuarioIdUser(UUID idUser);
}
