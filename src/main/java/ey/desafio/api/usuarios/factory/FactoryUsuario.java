package ey.desafio.api.usuarios.factory;

import java.util.List;

import ey.desafio.api.usuarios.dom.Telefono;
import ey.desafio.api.usuarios.dom.Usuario;
import ey.desafio.api.usuarios.to.ResponseUser;
import ey.desafio.api.usuarios.to.TOListUser;
import ey.desafio.api.usuarios.to.TOPhone;
import ey.desafio.api.usuarios.to.TOUser;

public class FactoryUsuario {

	private FactoryUsuario() {

	}

	public static ResponseUser mapReqUser(Usuario user) {
		return ResponseUser.builder().created(user.getCreated()).id(user.getIdUser().toString())
				.isactive(user.isActive()).last_login(user.getLastLogin()).modified(user.getModified())
				.token(user.getToken()).build();
	}

	public static Telefono mapPhone(TOPhone user) {
		return Telefono.builder().cityCode(user.getCitycode()).countryCode(user.getCountrycode())
				.number(user.getNumber()).build();
	}

	public static TOListUser mapToListUser(Usuario user) {
		return TOListUser.builder().email(user.getEmail()).name(user.getName()).uuid(user.getIdUser().toString())
				.token(user.getToken()).build();

	}

	public static TOUser mapUsuario(Usuario oU) {
		return TOUser.builder().email(oU.getEmail()).name(oU.getName()).token(oU.getToken())
				.uuid(oU.getIdUser().toString()).phones(FactoryUsuario.mapPhones(oU.getPhones()))
				.password((oU.getPassword().isBlank() ? null : "XXXXXXXX")).build();
	}

	public static List<TOPhone> mapPhones(List<Telefono> ePhone) {
		return ePhone.stream().map(FactoryUsuario::mapPhone).toList();
	}

	public static TOPhone mapPhone(Telefono ePhone) {
		return TOPhone.builder().number(ePhone.getNumber()).countrycode(ePhone.getCountryCode())
				.citycode(ePhone.getCityCode()).uuid(ePhone.getIdPhone()==null?"null":ePhone.getIdPhone().toString()).build();
	}
}
