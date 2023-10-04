package ey.desafio.api.usuarios.services;

import java.util.List;
import java.util.UUID;

import ey.desafio.api.usuarios.ex.ExEmailAlreadyAssign;
import ey.desafio.api.usuarios.ex.ExUserNotFound;
import ey.desafio.api.usuarios.to.RequestUserCreate;
import ey.desafio.api.usuarios.to.RequestUserUpdate;
import ey.desafio.api.usuarios.to.ResponseUser;
import ey.desafio.api.usuarios.to.TOListUser;
import ey.desafio.api.usuarios.to.TOUser;

public interface IServicioUsuario {

	List<TOListUser> getUsers();

	ResponseUser saveUser(RequestUserCreate user);

	int deleteUser(String email) throws ExUserNotFound;

	void updateUser(RequestUserUpdate usuario) throws ExUserNotFound, ExEmailAlreadyAssign;

	TOUser getUser(UUID oUuid) throws ExUserNotFound;

}
