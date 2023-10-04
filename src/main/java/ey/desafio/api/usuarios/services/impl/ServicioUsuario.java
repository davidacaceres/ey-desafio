package ey.desafio.api.usuarios.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ey.desafio.api.usuarios.dom.Telefono;
import ey.desafio.api.usuarios.dom.Usuario;
import ey.desafio.api.usuarios.ex.ExEmailAlreadyAssign;
import ey.desafio.api.usuarios.ex.ExUserNotFound;
import ey.desafio.api.usuarios.factory.FactoryUsuario;
import ey.desafio.api.usuarios.repo.IRepoTelefono;
import ey.desafio.api.usuarios.repo.IRepoUsuario;
import ey.desafio.api.usuarios.services.IServicioUsuario;
import ey.desafio.api.usuarios.to.RequestUserCreate;
import ey.desafio.api.usuarios.to.RequestUserUpdate;
import ey.desafio.api.usuarios.to.ResponseUser;
import ey.desafio.api.usuarios.to.TOListUser;
import ey.desafio.api.usuarios.to.TOPhone;
import ey.desafio.api.usuarios.to.TOUser;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServicioUsuario implements IServicioUsuario {

	@Autowired
	IRepoUsuario repoUser;

	@Autowired
	IRepoTelefono repoPhone;

	@Override
	@Transactional(readOnly = true)
	public List<TOListUser> getUsers() {
		return repoUser.findAll().stream().map(FactoryUsuario::mapToListUser).toList();
	}

	@Override
	@Transactional
	public ResponseUser saveUser(RequestUserCreate pUser) {
		String token = "";

		Usuario user = Usuario.builder().email(pUser.getEmail()).isActive(true).name(pUser.getName())
				.password(pUser.getPassword()).token(token).build();

		if (pUser.getPhones() != null && !pUser.getPhones().isEmpty()) {
			List<Telefono> ePhones = new ArrayList<>();
			for (TOPhone iPhone : pUser.getPhones()) {
				Telefono ePhone = FactoryUsuario.mapPhone(iPhone);
				ePhone.setUsuario(user);
				ePhones.add(ePhone);
			}
			user.setPhones(ePhones);
		}

		try {
			ResponseUser response = FactoryUsuario.mapReqUser(repoUser.saveAndFlush(user));
			System.out.println(user);
			response.setUser(FactoryUsuario.mapUsuario(user));
			return response;
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException ex = (ConstraintViolationException) e.getCause();

				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	@Transactional
	public int deleteUser(String email) throws ExUserNotFound {
		if (repoUser.existsByEmail(email)) {
			return repoUser.deleteByEmail(email);
		} else {
			throw new ExUserNotFound("Email no encontrado");
		}
	}

	@Override
	@Transactional
	public void updateUser(RequestUserUpdate usuario) throws ExUserNotFound, ExEmailAlreadyAssign {
		UUID uuid = UUID.fromString(usuario.getUuid());
		Optional<Usuario> oUsuario = repoUser.findById(uuid);
		if (oUsuario.isEmpty())
			throw new ExUserNotFound(String.format("No se encuentra un usuario con uuid:%s", uuid));
		Usuario eUsuario = oUsuario.get();
		if (!eUsuario.getEmail().equals(usuario.getEmail())) {
			boolean existeEmail = repoUser.existsByEmailAndIdUserNot(usuario.getEmail(), uuid);
			if (existeEmail) {
				throw new ExEmailAlreadyAssign("El email ya se encuentra asignado a otro usuario");
			}
		}
		eUsuario.setEmail(usuario.getEmail());
		eUsuario.setName(usuario.getName());
		eUsuario.setPassword(usuario.getPassword());
		List<Telefono> currentPhones = new ArrayList<>(eUsuario.getPhones());
//		eUsuario.setToken();
		if (usuario.getPhones() == null
				|| usuario.getPhones().isEmpty() && currentPhones != null && !currentPhones.isEmpty()) {
			eUsuario.getPhones().removeAll(currentPhones);
		} else {
			List<Telefono> ePhones = new ArrayList<>();
			for (TOPhone oPhone : usuario.getPhones()) {
				Telefono ePhone = FactoryUsuario.mapPhone(oPhone);
				ePhone.setUsuario(eUsuario);
				ePhones.add(ePhone);
			}
			currentPhones.removeAll(ePhones);
			for (Telefono removePhone : currentPhones) {
				eUsuario.getPhones().remove(removePhone);
			}
			eUsuario.getPhones().addAll(ePhones);

		}
		repoUser.saveAndFlush(eUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public TOUser getUser(UUID oUuid) throws ExUserNotFound {
		Usuario oUsuario = repoUser.findById(oUuid)
				.orElseThrow(() -> new ExUserNotFound("No se encuentra un usuario con el uuid asociado"));
		return FactoryUsuario.mapUsuario(oUsuario);
	}

}
