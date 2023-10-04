package ey.desafio.api.usuarios.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ey.desafio.api.usuarios.ex.ExEmailAlreadyAssign;
import ey.desafio.api.usuarios.ex.ExUserNotFound;
import ey.desafio.api.usuarios.ex.ExUuidInvalid;
import ey.desafio.api.usuarios.ex.ExUuidNotFound;
import ey.desafio.api.usuarios.to.RequestUserCreate;
import ey.desafio.api.usuarios.to.RequestUserUpdate;
import ey.desafio.api.usuarios.to.ResponseUser;
import ey.desafio.api.usuarios.to.TOListUser;
import ey.desafio.api.usuarios.to.TOUser;

/**
 * Interfaz de Controlador de Usuario, permite listar y guardar nuevos usuarios
 * 
 * @author dcaceres
 *
 */
public interface IControladorUsuario {
	/**
	 * Metodo que entrega la lista de usuarios
	 * 
	 * @return List<ResponseUser> {@link ResponseUser}
	 */
	ResponseEntity<List<TOListUser>> getUsers();

	/**
	 * Metodo que permite la creacion de un usuario
	 * 
	 * @param usuario {@link RequestUserCreate}
	 * @return {@link ResponseUser}
	 */
	ResponseEntity<ResponseUser> postUser(RequestUserCreate usuario);

	/**
	 * Metodo que permite la eliminacion de un usuario por el email
	 * 
	 * @param usuario {@link RequestUserCreate}
	 * @return {@link ResponseUser}
	 */
	ResponseEntity<?> deleteUser(String email) throws ExUserNotFound;

	/**
	 * Metodo que permitei actualizar un usuario en base al uuid.
	 * 
	 * @param usuario
	 * @return {@link HttpStatus} 204 cuando se actualiza con exito, 400 cuando
	 *         existe un error en los datos
	 * @throws ExUuidNotFound
	 * @throws ExEmailAlreadyAssign
	 */
	ResponseEntity<?> putUser(RequestUserUpdate usuario) throws ExUserNotFound, ExUuidNotFound, ExEmailAlreadyAssign;

	ResponseEntity<TOUser> getUser(String uuid) throws ExUuidNotFound, ExUuidInvalid, ExUserNotFound;

}
