package ey.desafio.api.usuarios.controller.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ey.desafio.api.usuarios.controller.IControladorUsuario;
import ey.desafio.api.usuarios.ex.ExEmailAlreadyAssign;
import ey.desafio.api.usuarios.ex.ExUserNotFound;
import ey.desafio.api.usuarios.ex.ExUuidInvalid;
import ey.desafio.api.usuarios.ex.ExUuidNotFound;
import ey.desafio.api.usuarios.services.IServicioUsuario;
import ey.desafio.api.usuarios.to.RequestUserCreate;
import ey.desafio.api.usuarios.to.RequestUserUpdate;
import ey.desafio.api.usuarios.to.ResponseUser;
import ey.desafio.api.usuarios.to.TOListUser;
import ey.desafio.api.usuarios.to.TOUser;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/api/v1/usuario")
@Slf4j
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.OPTIONS })
public class ControladorUsuario implements IControladorUsuario {

	@Autowired
	private IServicioUsuario servUsuario;

	@PostConstruct
	private void init() {
		log.debug("Controlador de usuario inicializado");
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<TOListUser>> getUsers() {
		log.debug("Listando usuarios");
		List<TOListUser> users = servUsuario.getUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{uuid}")
	@Override
	public ResponseEntity<TOUser> getUser(
			@Valid @PathVariable @NotBlank(message = "Debe ingresar el uuid del usuario") String uuid) throws ExUuidInvalid, ExUserNotFound {
		log.debug("Obteniendo usuario {}", uuid);
		UUID oUuid = null;
		try {
			oUuid = UUID.fromString(uuid);
		} catch (IllegalArgumentException e) {
			throw new ExUuidInvalid("uuid proporcionado no es valido");
		}

		TOUser user = servUsuario.getUser(oUuid);
		return ResponseEntity.ok(user);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<ResponseUser> postUser(@Valid @RequestBody RequestUserCreate usuario) {
		log.debug("Creando usuario");
		ResponseUser response = servUsuario.saveUser(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{email}")
	@Override
	@Validated
	public ResponseEntity<?> deleteUser(@Valid @PathVariable @NotBlank(message = "Debe ingresar un email") String email)
			throws ExUserNotFound {
		log.debug("eliminacion de usuario {}", email);
		int id = servUsuario.deleteUser(email);
		if (id == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<?> putUser(@Valid @RequestBody RequestUserUpdate usuario)
			throws ExUserNotFound, ExUuidNotFound, ExEmailAlreadyAssign {
		if (usuario == null || usuario.getUuid() == null || usuario.getUuid().isBlank())
			throw new ExUuidNotFound("Se debe indicar el uuid del usuario");
		log.debug("Actualizando usuario {}", usuario.getUuid());
		servUsuario.updateUser(usuario);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
